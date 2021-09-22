package com.example.controller.commands;

import com.example.datasource.FlightDataMapper;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteFlightCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            FlightDataMapper.getInstance().deleteById(id);
        } catch (Exception e) {
            // TODO: send error message to front
            e.printStackTrace();
        }
        forward("/fourAces?command=GetFlights");
    }

    @Override
    public void processPost() throws ServletException, IOException {

    }
}
