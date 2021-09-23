package com.example.controller.commands;

import com.example.datasource.FlightDataMapper;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteFlightCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        int airlineId = Integer.parseInt(request.getParameter("airlineId"));
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        try {
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
