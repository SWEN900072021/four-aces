package com.example.four_aces.controller.commands;

import com.example.four_aces.datasource.FlightMapper;
import com.example.four_aces.domain.Flight;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class SearchFlightCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String date = request.getParameter("flightDate");
        String time = request.getParameter("flightTime");
        FlightMapper flightMapper = new FlightMapper();
        List<Flight> flights = flightMapper.searchFlights(date, time);
        if (flights.size() > 0) {
            request.setAttribute("flights", flights);
        }
        forward("/viewFlights.jsp");
    }

}
