package com.example.controller.commands;

import com.example.datasource.CustomerDataMapper;
import com.example.datasource.PassengerDataMapper;
import com.example.datasource.ReservationDataMapper;
import com.example.domain.Customer;
import com.example.domain.Flight;
import com.example.domain.Passenger;
import com.example.domain.Reservation;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewPassengerCommand extends AirlineCommand {

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), new PrivilegedAction<Flight>() {
            @Override
            public Flight run() {
                String flightId = request.getParameter("flightId");
                String condition = String.format("WHERE go_flight = '%s' OR return_flight = '%s'",flightId, flightId);
                try {
                    ArrayList<Reservation> reservations = ReservationDataMapper.getInstance().find(condition);
                    ArrayList<Integer> passengersId = ReservationDataMapper.getInstance().getPassengersIdByReservations(reservations);
                    ArrayList<Passenger> passengers = new ArrayList<>();
                    for (Integer passengerId : passengersId) {
                        passengers.add(PassengerDataMapper.getInstance().findById(passengerId));
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
