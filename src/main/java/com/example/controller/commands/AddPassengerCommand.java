package com.example.controller.commands;

import com.example.controller.BookingController;
import com.example.datasource.*;
import com.example.domain.*;
import com.example.exception.NoRecordFoundException;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddPassengerCommand extends CustomerCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        forward("/addPassenger.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String idType = request.getParameter("idType");
            String idNum = request.getParameter("idNum");
            try{
                //UnitOfWork unitOfWork = (UnitOfWork) request.getSession().getAttribute("unitOfWork");
                BookingUnitOfWork bookingUnitOfWork = (BookingUnitOfWork) request.getSession().getAttribute("bookingUnitOfWork");
                Passenger passenger = new Passenger(null, firstName, lastName, idType, idNum);
                bookingUnitOfWork.registerPassenger(passenger);
                Reservation reservation = bookingUnitOfWork.getReservation();
                bookingUnitOfWork.setCurrentPassenger(passenger);
                //reservation.addPassenger(passenger);
                Flight flight = reservation.getGoFlight();
                List<Ticket> tickets = BookingController.getInstance().getAvailableTickets(flight);
                //request.setAttribute("passenger", passenger);
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
