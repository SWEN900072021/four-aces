package com.example.controller.commands;

import com.example.datasource.FlightDataMapper;
import com.example.datasource.TicketDataMapper;
import com.example.domain.Flight;
import com.example.domain.Ticket;
import com.example.domain.UnitOfWork;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class DeleteFlightCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        int airlineId = Integer.parseInt(request.getParameter("airlineId"));
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        try {
            UnitOfWork uow = UnitOfWork.getInstance();
            // Delete all tickets of the flight before deleting the flight
            TicketDataMapper ticketDataMapper = TicketDataMapper.getInstance();
            List<Ticket> tickets = ticketDataMapper.getAll(flightId);
            for (int i = 0; i < tickets.size(); i++) {
                    uow.getInstance().registerDeleted(tickets.get(i));
            }
            Flight flight = FlightDataMapper.getInstance().findById(flightId);
            uow.getInstance().registerDeleted(flight);
            uow.commit();
        } catch (Exception e) {
            // TODO: send error message to front
            e.printStackTrace();
        }
        forward("/fourAces?command=GetFlight&airlineId=" + airlineId);
    }

    @Override
    public void processPost() throws ServletException, IOException {

    }
}
