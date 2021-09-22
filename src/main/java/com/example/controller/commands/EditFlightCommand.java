package com.example.controller.commands;

import com.example.datasource.FlightDataMapper;
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
        Flight flight = null;
        try {
            flight = FlightDataMapper.getInstance().findById(id);
            flight.setCode(flightCode);
            flight.setDate(flightDate);
            flight.setTime(flightTime);
            UnitOfWork.getInstance().commit();
        } catch (Exception e) {
            // TODO: send error message
            e.printStackTrace();
        }
    }

    @Override
    public void processPost() throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String flightCode = request.getParameter("flightCode");
        String flightDate= request.getParameter("flightDate");
        String flightTime = request.getParameter("flightTime");
        Flight flight = null;
        try {
            flight = FlightDataMapper.getInstance().findById(id);
            flight.setCode(flightCode);
            flight.setDate(flightDate);
            flight.setTime(flightTime);
            UnitOfWork.getInstance().commit();
            response.sendRedirect("fourAces?command=GetFlights");
        } catch (Exception e) {
            // TODO: send error message
            e.printStackTrace();
        }
    }
}

