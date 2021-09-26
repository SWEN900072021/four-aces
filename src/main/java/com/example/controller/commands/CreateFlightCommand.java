package com.example.controller.commands;

import com.example.domain.Airline;
import com.example.domain.UnitOfWork;
import com.example.exception.AccessDeniedException;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;

//TODO
public class CreateFlightCommand extends AirlineCommand {

    @Override
    public void processGet() throws ServletException, IOException {
        forward("/createFlight.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            try {
                Airline airline = getCurrentUser();
                String flightCode = request.getParameter("flightCode");
                String flightDate = request.getParameter("flightDate");
                String flightTime = request.getParameter("flightTime");
                int source = Integer.parseInt(request.getParameter("source"));
                int destination = Integer.parseInt(request.getParameter("destination"));
                int airplaneId = Integer.parseInt(request.getParameter("airplane"));
                airline.createFlight(flightCode, flightDate, flightTime, source, destination, airplaneId);
                UnitOfWork.getInstance().commit();
                forward("/airline.jsp");
            } catch (Exception e) {
                error(e);
            }
            return null;
        });
    }
}
