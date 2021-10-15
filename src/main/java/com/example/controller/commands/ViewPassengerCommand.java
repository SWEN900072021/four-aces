package com.example.controller.commands;

import com.example.datasource.CustomerDataMapper;
import com.example.datasource.PassengerDataMapper;
import com.example.datasource.ReservationDataMapper;
import com.example.datasource.TicketDataMapper;
import com.example.domain.*;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ViewPassengerCommand extends AirlineCommand {

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), new PrivilegedAction<Flight>() {
            @Override
            public Flight run() {
                String flightId = request.getParameter("flightId");
                StringBuilder condition = new StringBuilder(String.format("WHERE go_flight = '%s' OR return_flight = '%s'", flightId, flightId));
                try {
                    Iterator<Reservation> reservations = ReservationDataMapper.getInstance().find(condition.toString()).iterator();
                    condition = new StringBuilder(String.format("WHERE reservation_id = '%d'", reservations.next().getId()));
                    while(reservations.hasNext()){
                        condition.append(String.format(" OR reservation_id = '%d'", reservations.next().getId()));
                    }
                    ArrayList<Ticket> tickets = TicketDataMapper.getInstance().find(condition.toString());
                    ArrayList<Passenger> passengers = new ArrayList<>();
                    for (Ticket ticket : tickets) {
                        passengers.add(PassengerDataMapper.getInstance().findById(ticket.getPassenger().getId()));
                    }
                    request.setAttribute("passengers", passengers);
                } catch (Exception e) {
                    error(e);
                }
                return null;
            }
        });
        forward("/viewPassenger.jsp");
    }
}
