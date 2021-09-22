package com.example.controller.commands;

import com.example.domain.Flight;
import com.example.domain.UnitOfWork;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

public class CreateFlightCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
    }

    @Override
    public void processPost() throws ServletException, IOException {
        String flightCode = request.getParameter("flightCode");
        String flightDate= request.getParameter("flightDate");
        String flightTime = request.getParameter("flightTime");
        new Flight(null, flightCode, flightDate, flightTime);
        try {
            UnitOfWork.getInstance().commit();
        } catch (Exception e) {
            // TODO: send error message
            e.printStackTrace();
        }
        forward("/airline.jsp");
    }
}
