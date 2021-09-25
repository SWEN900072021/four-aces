package com.example.controller.commands;

import com.example.datasource.FlightDataMapper;
import com.example.datasource.TicketDataMapper;
import com.example.domain.Ticket;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class DeleteFlightCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        int airlineId = Integer.parseInt(request.getParameter("airlineId"));
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        try {
            // Delete all tickets of the flight before deleting the flight
            TicketDataMapper ticketDataMapper = TicketDataMapper.getInstance();
            List<Ticket> tickets = ticketDataMapper.getAllByFlightId(flightId);
            if (tickets.size() > 0) {
                for (int i = 0; i < tickets.size(); i++) {
                    ticketDataMapper.deleteById(tickets.get(i).getId());
                }
            }
            FlightDataMapper.getInstance().deleteById(flightId);
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
