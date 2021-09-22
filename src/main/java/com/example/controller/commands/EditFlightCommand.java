package com.example.controller.commands;

import com.example.datasource.FlightMapper;
import com.example.domain.Flight;
import com.example.domain.UnitOfWork;

import javax.servlet.ServletException;
import java.io.IOException;

public class EditFlightCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String flightCode = request.getParameter("flightCode");
        String flightDate= request.getParameter("flightDate");
        String flightTime = request.getParameter("flightTime");
        Flight flight = FlightMapper.getInstance().findById(id);
        flight.setCode(flightCode);
        flight.setDate(flightDate);
        flight.setTime(flightTime);
        UnitOfWork.getInstance().commit();
        forward("/fourAces?command=GetFlights");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String flightCode = request.getParameter("flightCode");
        String flightDate= request.getParameter("flightDate");
        String flightTime = request.getParameter("flightTime");
        Flight flight = FlightMapper.getInstance().findById(id);
        flight.setCode(flightCode);
        flight.setDate(flightDate);
        flight.setTime(flightTime);
        UnitOfWork.getInstance().commit();
        forward("/frontServlet?command=GetFlights");
    }
}

