package com.example.controller.commands;

import com.example.datasource.FlightMapper;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteFlightCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        FlightMapper.getInstance().deleteById(id);
        forward("/fourAces?command=GetFlights");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        FlightMapper.getInstance().deleteById(id);
        forward("/fourAces?command=GetFlights");
    }
}
