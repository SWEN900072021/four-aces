package com.example.controller.commands;

import com.example.datasource.FlightDataMapper;
import com.example.domain.Airline;
import com.example.domain.Flight;
import com.example.domain.UnitOfWork;
import com.example.exception.AccessDeniedException;

import javax.servlet.ServletException;
import java.io.IOException;

public class EditFlightCommand extends AirlineCommand {
    @Override
    public void processGet() throws ServletException, IOException {
            Airline airline = (Airline) request.getSession(false).getAttribute("user");
            forward("/editFlight.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        try {
            Airline airline = (Airline) request.getSession(false).getAttribute("user");
            int flightId = Integer.parseInt(request.getParameter("flightId"));
            String flightCode = request.getParameter("flightCode");
            String flightDate= request.getParameter("flightDate");
            String flightTime = request.getParameter("flightTime");
            Flight flight = null;
            flight = FlightDataMapper.getInstance().findById(flightId);
            flight.setCode(flightCode);
            flight.setDate(flightDate);
            flight.setTime(flightTime);
            UnitOfWork.getInstance().commit();
            response.sendRedirect("fourAces?command=GetFlight");
        } catch ( AccessDeniedException e ){
            forward("/login.jsp");
        }
        catch (Exception e) {
            // TODO: send error message
            e.printStackTrace();
        }
    }
}

