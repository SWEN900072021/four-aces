package com.example.controller.commands;

import com.example.datasource.AirportDataMapper;
import com.example.datasource.FlightDataMapper;
import com.example.domain.Airline;
import com.example.domain.Airport;
import com.example.domain.Flight;
import com.example.exception.TRSException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetFlightCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        try {
            Airline airline = (Airline) request.getSession().getAttribute("user");
            if( airline == null ){
                response.sendRedirect("/login.jsp");
                throw new TRSException("Access Denied");
            }
            List<Flight> allFlights = FlightDataMapper.getInstance().getAll();
            // Only forward flights created by airline with id equals airlineId
            List<Flight> flights = new ArrayList<>();
            for (Flight flight : allFlights) {
                if (flight.getAirlineId() == (int) airline.getId()) {
                    flights.add(flight);
                }
            }
            request.setAttribute("flights", flights);
        } catch (Exception e) {
            request.setAttribute("flights", new ArrayList<Flight>());
            error(e);
        }
        forward("/flights.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
    }
}

