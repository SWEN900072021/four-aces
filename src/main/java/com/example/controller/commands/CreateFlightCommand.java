package com.example.controller.commands;

import com.example.domain.Flight;
import com.example.domain.UnitOfWork;

import javax.servlet.ServletException;
import java.io.IOException;

public class CreateFlightCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        String flightCode = request.getParameter("flightCode");
        String flightDate= request.getParameter("flightDate");
        String flightTime = request.getParameter("flightTime");
        Flight flight = new Flight(null, flightCode, flightDate, flightTime);
        UnitOfWork.getInstance().commit();
        forward("/home.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        String flightCode = request.getParameter("flightCode");
        String flightDate= request.getParameter("flightDate");
        String flightTime = request.getParameter("flightTime");
        Flight flight = new Flight(null, flightCode, flightDate, flightTime);
        UnitOfWork.getInstance().commit();
        forward("/home.jsp");
    }
}
