package com.example.controller.commands;

import com.example.datasource.FlightMapper;
import com.example.domain.Flight;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class GetFlightsCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        List<Flight> flights = FlightMapper.getInstance().getAll();
        if (flights.size() > 0) {
            request.setAttribute("flights", flights);
        }
        forward("/flights.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
    }
}

