package com.example.controller.commands;

import com.example.datasource.FlightDataMapper;
import com.example.domain.Airline;
import com.example.domain.Flight;
import com.example.domain.UnitOfWork;
import com.example.exception.AccessDeniedException;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;

public class EditFlightCommand extends AirlineCommand {
    @Override
    public void processGet() throws ServletException, IOException {
    }

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            try {
                if (request.getParameter("forward") != null){
                    forward("/editFlight.jsp");
                    return null;
                }
                int flightId = Integer.parseInt(request.getParameter("flightId"));
                String flightCode = request.getParameter("flightCode");
                String flightDate = request.getParameter("flightDate");
                String flightTime = request.getParameter("flightTime");
                int destination = Integer.parseInt(request.getParameter("destination"));
                int source = Integer.parseInt(request.getParameter("source"));
                Flight flight = null;
                flight = FlightDataMapper.getInstance().findById(flightId);
                flight.setCode(flightCode);
                flight.setDate(flightDate);
                flight.setTime(flightTime);
                flight.setSource(source);
                flight.setDestination(destination);
                UnitOfWork.getInstance().commit();
            } catch (Exception e) {
                error(e);
            }
            return null;
        });
        response.sendRedirect("fourAces?command=GetFlight");
    }
}

