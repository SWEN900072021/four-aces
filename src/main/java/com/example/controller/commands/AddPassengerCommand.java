package com.example.controller.commands;

import com.example.controller.BookingController;
import com.example.datasource.*;
import com.example.domain.*;
import com.example.exception.NoRecordFoundException;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.List;

public class AddPassengerCommand extends CustomerCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        forward("/addPassenger.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String idType = request.getParameter("idType");
        String idNum = request.getParameter("idNum");
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            try{
                Customer customer = getCurrentUser();
                UnitOfWork unitOfWork = UnitOfWork.getCurrent();
                Passenger savedPassenger;
                HashMap<String, String> params = new HashMap<>();
                params.put("passenger_firstname", firstName);
                params.put("passenger_lastname", lastName);
                params.put("identificationtype", idType);
                params.put("identificationnumber", idNum);
                try {
                    savedPassenger = PassengerDataMapper.getInstance().find(params).get(0);
                } catch (NoRecordFoundException e) {
                    e.printStackTrace();
                    Passenger passenger = new Passenger(null, firstName, lastName, idType, idNum);
                    PassengerDataMapper.getInstance().insert(passenger);
                    savedPassenger = PassengerDataMapper.getInstance().find(params).get(0);
                }
                Reservation reservation = (Reservation) unitOfWork.getNewObjectOf("Reservation");

                Flight flight = reservation.getGoFlight();
                List<Ticket> tickets = BookingController.getInstance().getAvailableTickets(flight);
                request.setAttribute("passenger", savedPassenger);
                request.setAttribute("tickets", tickets);
                request.setAttribute("type", "go");
                request.setAttribute("returning", reservation.isReturning());
                forward("/chooseSeats.jsp");
            } catch (Exception e) {
                error(e);
            }
            return null;
        });
    }
}
