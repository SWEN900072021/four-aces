package com.example.controller.commands;

import com.example.domain.Flight;
import com.example.datasource.FlightMapper;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class ViewFlightCommand extends FrontCommand {

    @Override
    public void processGet() throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("flight_id"));
        FlightMapper flightMapper = new FlightMapper();
        Flight flight = flightMapper.findById(id);
        if (flight != null) {
            request.setAttribute("flight", flight);
        }
        forward("/viewFlight.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("flight_id"));
        FlightMapper flightMapper = new FlightMapper();
        Flight flight = flightMapper.findById(id);
        if (flight != null) {
            request.setAttribute("flight", flight);
        }
        forward("/viewFlight.jsp");
    }

}
