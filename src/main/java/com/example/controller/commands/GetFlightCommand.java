package com.example.controller.commands;

import com.example.domain.Airline;
import com.example.domain.Flight;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

public class GetFlightCommand extends AirlineCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            try {
                Airline airline = getCurrentUser();
                List<Flight> flights = airline.getFlights();
                request.setAttribute("flights", flights);
            } catch (Exception e) {
                request.setAttribute("flights", new ArrayList<Flight>());
                error(e);
            }
            return null;
        });
        forward("/flights.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
    }
}

