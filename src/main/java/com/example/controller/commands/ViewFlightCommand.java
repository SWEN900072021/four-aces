package com.example.controller.commands;

import com.example.datasource.FlightDataMapper;
import com.example.domain.Flight;

import javax.servlet.ServletException;
import java.io.IOException;

public class ViewFlightCommand extends CustomerCommand {

    @Override
    public void processGet() throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("flightId"));
        FlightDataMapper flightDataMapper = FlightDataMapper.getInstance();
        try {
            Flight flight = flightDataMapper.findById(id);
            if (flight != null) {
                request.setAttribute("flight", flight);
            }
            forward("/viewFlight.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processPost() throws ServletException, IOException {
    }

}
