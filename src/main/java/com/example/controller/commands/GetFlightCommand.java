package com.example.controller.commands;

import com.example.datasource.AirportDataMapper;
import com.example.datasource.FlightDataMapper;
import com.example.domain.Airline;
import com.example.domain.Airport;
import com.example.domain.Flight;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetFlightCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        int airlineId = Integer.parseInt(request.getParameter("airlineId"));
        try {
            List<Flight> allFlights = FlightDataMapper.getInstance().getAll();
            List<Airport> airports = AirportDataMapper.getInstance().getAll();
            // Only forward flights created by airline with id equals airlineId
            List<Flight> flights = new ArrayList<>();
            for (int i = 0; i < allFlights.size(); i ++) {
                Flight flight = allFlights.get(i);
                if (flight.getAirlineId() == airlineId) {
                    flights.add(flight);
                }
            }
            request.setAttribute("flights", flights);
            request.setAttribute("airports", airports);
        } catch (Exception e) {
            request.setAttribute("flights", new ArrayList<Flight>());
            request.setAttribute("airports", new ArrayList<Airport>());
            e.printStackTrace();
        }
        forward("/flights.jsp?airlineId=" + airlineId);
    }

    @Override
    public void processPost() throws ServletException, IOException {
    }
}

