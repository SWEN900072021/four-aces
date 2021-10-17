package com.example.controller.commands;

import com.example.concurrency.LockManager;
import com.example.datasource.FlightDataMapper;
import com.example.datasource.ReservationDataMapper;
import com.example.datasource.TicketDataMapper;
import com.example.domain.*;
import com.example.exception.NoRecordFoundException;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeleteFlightCommand extends AirlineCommand {
    @Override
    public void processGet() throws ServletException, IOException {
    }

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            try {
                int flightId = Integer.parseInt(request.getParameter("flightId"));

                UnitOfWork.newCurrent();
                TicketDataMapper ticketDataMapper = TicketDataMapper.getInstance();
                ReservationDataMapper reservationDataMapper = ReservationDataMapper.getInstance();

                String httpSessionId = request.getSession(true).getId();
                LockManager lockManager = LockManager.getInstance();

                // Find all tickets of the flight
                List<Ticket> tickets = new ArrayList<>();
                try {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("flight_id", flightId+"");
                    tickets = ticketDataMapper.find(params);
                } catch (NoRecordFoundException e) {
                    System.out.println("Flight " + flightId + " has no ticket.");
                }
                // Delete all tickets of the flight
                for (Ticket ticket : tickets) {
                    // Delete reservation associated with the ticket
                    if (ticket.getReservation() != null) {
                        int reservationId = ticket.getReservation().getId();
                        Reservation reservation = reservationDataMapper.findById(reservationId);
                        UnitOfWork.getCurrent().registerDeleted(reservation);
                    }
                    lockManager.acquireLock("ticket-" + ticket.getId(), httpSessionId);
                    UnitOfWork.getCurrent().registerDeleted(ticket);
                }
                // Delete flight
                lockManager.acquireLock("flight-" + flightId, httpSessionId);
                Flight flight = FlightDataMapper.getInstance().findById(flightId);
                UnitOfWork.getCurrent().registerDeleted(flight);
                UnitOfWork.getCurrent().commit();
                // Release lock
                for (Ticket ticket : tickets) {
                    lockManager.releaseLock("ticket-" + ticket.getId(), httpSessionId);
                }
                lockManager.releaseLock("flight-" + flightId, httpSessionId);
            } catch (Exception e) {
                e.printStackTrace();
                request.getSession().setAttribute("error", "Unable to delete flight or flight has been deleted. " + e);
            }
            return null;
        });
        response.sendRedirect("fourAces?command=GetFlight");
    }
}
