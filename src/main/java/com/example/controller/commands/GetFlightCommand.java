package com.example.controller.commands;

import com.example.datasource.AirportDataMapper;
import com.example.datasource.FlightDataMapper;
import com.example.domain.Airport;
import com.example.domain.Flight;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetFlightCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        try {
            List<Flight> flights = FlightDataMapper.getInstance().getAll();
            List<Airport> airports = AirportDataMapper.getInstance().getAll();
            request.setAttribute("flights", flights);
            request.setAttribute("airports", airports);
        } catch (Exception e) {
            request.setAttribute("flights", new ArrayList<Flight>());
            request.setAttribute("airports", new ArrayList<Airport>());
            e.printStackTrace();
        }
        forward("/flights.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
    }
}

