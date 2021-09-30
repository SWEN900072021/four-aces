package com.example.controller.commands;

import com.example.datasource.AirlineDataMapper;
import com.example.datasource.AirportDataMapper;
import com.example.datasource.FlightDataMapper;
import com.example.domain.Airline;
import com.example.domain.Airport;
import com.example.domain.Flight;
import com.example.exception.TRSException;

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
                List<Airport> airports = AirportDataMapper.getInstance().getAll();
                List<Flight> flights = airline.getFlights();
                request.setAttribute("flights", flights);
                request.setAttribute("airports", airports);
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

