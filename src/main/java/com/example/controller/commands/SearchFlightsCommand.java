package com.example.controller.commands;

import com.example.domain.Flight;
import com.example.datasource.FlightMapper;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class SearchFlightsCommand extends FrontCommand {

    @Override
    public void processGet() throws ServletException, IOException {
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        FlightMapper flightMapper = new FlightMapper();
        List<Flight> flights = flightMapper.searchFlights(date, time);
        if (flights.size() > 0) {
            request.setAttribute("flights", flights);
        }
        forward("/searchFlightsResult.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        FlightMapper flightMapper = new FlightMapper();
        List<Flight> flights = flightMapper.searchFlights(date, time);
        if (flights.size() > 0) {
            request.setAttribute("flights", flights);
        }
        forward("/searchFlightsResult.jsp");
    }

}
