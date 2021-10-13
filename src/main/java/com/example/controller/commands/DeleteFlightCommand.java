package com.example.controller.commands;

import com.example.concurrency.LockManager;
import com.example.datasource.FlightDataMapper;
import com.example.datasource.TicketDataMapper;
import com.example.domain.Airline;
import com.example.domain.Flight;
import com.example.domain.Ticket;
import com.example.domain.UnitOfWork;
import com.example.exception.NoRecordFoundException;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.PrivilegedAction;
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
                UnitOfWork.newCurrent();

                int flightId = Integer.parseInt(request.getParameter("flightId"));
                // Delete all tickets of the flight before deleting the flight
                TicketDataMapper ticketDataMapper = TicketDataMapper.getInstance();
                HashMap<String, String> params = new HashMap<>();
                params.put("flight_id", flightId+"");
                try {
                    List<Ticket> tickets = ticketDataMapper.find(params);
                    for (Ticket ticket : tickets) {
                        UnitOfWork.getCurrent().registerDeleted(ticket);
                    }
                } catch (NoRecordFoundException e) {
//                    e.printStackTrace();
                }
                String httpSessionId = request.getSession(true).getId();
                LockManager.getInstance().acquireLock("flight-" + flightId, httpSessionId);

                Flight flight = FlightDataMapper.getInstance().findById(flightId);
                UnitOfWork.getCurrent().registerDeleted(flight);
                UnitOfWork.getCurrent().commit();

                LockManager.getInstance().releaseLock("flight-" + flightId, httpSessionId);
            } catch (Exception e) {
                e.printStackTrace();
                request.getSession().setAttribute("error", "Unable to delete flight. " + e);
            }
            return null;
        });
        response.sendRedirect("fourAces?command=GetFlight");
    }
}
