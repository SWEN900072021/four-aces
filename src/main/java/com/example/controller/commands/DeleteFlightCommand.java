package com.example.controller.commands;

import com.example.datasource.FlightDataMapper;
import com.example.datasource.TicketDataMapper;
import com.example.domain.Flight;
import com.example.domain.Ticket;
import com.example.domain.UnitOfWork;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class DeleteFlightCommand extends AirlineCommand {
    @Override
    public void processGet() throws ServletException, IOException {
    }

    @Override
    public void processPost() throws ServletException, IOException {
        try {
            int flightId = Integer.parseInt(request.getParameter("flightId"));
            // Delete all tickets of the flight before deleting the flight
            TicketDataMapper ticketDataMapper = TicketDataMapper.getInstance();
            List<Ticket> tickets = ticketDataMapper.getAll(flightId);
            for (Ticket ticket : tickets) {
                UnitOfWork.getInstance().registerDeleted(ticket);
            }
            Flight flight = FlightDataMapper.getInstance().findById(flightId);
            UnitOfWork.getInstance().registerDeleted(flight);
            UnitOfWork.getInstance().commit();
        } catch (Exception e) {
            forward("/login.jsp");
        }
        forward("/fourAces?command=GetFlight");

    }
}
