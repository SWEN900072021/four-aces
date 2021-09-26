package com.example.controller.commands;

import com.example.datasource.FlightDataMapper;
import com.example.domain.Flight;
import com.example.exception.TRSException;

import javax.servlet.ServletException;
import java.io.IOException;

public class ViewFlightCommand extends CustomerCommand {

    @Override
    public void processGet() throws ServletException, IOException {
        Integer customerId = Integer.parseInt(request.getParameter("customerId"));
        Integer flightId = Integer.parseInt(request.getParameter("flightId"));
        String type = request.getParameter("type");
        FlightDataMapper flightDataMapper = FlightDataMapper.getInstance();
        try {
            Flight flight = flightDataMapper.findById(flightId);
            if (flight != null) {
                request.setAttribute("flight", flight);
                request.setAttribute("type", type);
            }

            forward("/viewFlight.jsp?customerId=" + customerId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processPost() throws ServletException, IOException {
    }

}
