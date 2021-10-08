package com.example.controller.commands;

import com.example.datasource.FlightDataMapper;
import com.example.datasource.TicketDataMapper;
import com.example.domain.Flight;
import com.example.domain.Ticket;
import com.example.domain.UnitOfWork;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.List;
//TODO
public class DeleteFlightCommand extends AirlineCommand {
    @Override
    public void processGet() throws ServletException, IOException {
    }

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            try {
                UnitOfWork.newCurrent();
                int flightId = Integer.parseInt(request.getParameter("flightId"));
                // Delete all tickets of the flight before deleting the flight
                TicketDataMapper ticketDataMapper = TicketDataMapper.getInstance();
                List<Ticket> tickets = ticketDataMapper.getAll(flightId);
                for (Ticket ticket : tickets) {
                    UnitOfWork.getCurrent().registerDeleted(ticket);
                }
                Flight flight = FlightDataMapper.getInstance().findById(flightId);
                UnitOfWork.getCurrent().registerDeleted(flight);
                UnitOfWork.getCurrent().commit();
            } catch (Exception e) {
                error(e);
            }
            return null;
        });
        response.sendRedirect("/fourAces?command=GetFlight");

    }
}
