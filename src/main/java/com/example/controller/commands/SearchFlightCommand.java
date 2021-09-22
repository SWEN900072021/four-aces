package com.example.controller.commands;

import com.example.domain.Flight;
import com.example.datasource.FlightMapper;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class SearchFlightCommand extends FrontCommand {

    @Override
    public void processGet() throws ServletException, IOException {
        String date = request.getParameter("flightDate");
        String time = request.getParameter("flightTime");
        FlightMapper flightMapper = new FlightMapper();
        List<Flight> flights = flightMapper.searchFlights(date, time);
        if (flights.size() > 0) {
            request.setAttribute("flights", flights);
        }
        forward("/viewFlights.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        List<Flight> flights = FlightMapper.getInstance().getAll();
        if (flights.size() > 0) {
            request.setAttribute("flights", flights);
        }
        forward("/flights.jsp");
    }

}
