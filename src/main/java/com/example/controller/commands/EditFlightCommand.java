package com.example.controller.commands;

import com.example.datasource.AirplaneDataMapper;
import com.example.datasource.AirportDataMapper;
import com.example.datasource.FlightDataMapper;
import com.example.domain.Airplane;
import com.example.domain.Airport;
import com.example.domain.Flight;
import com.example.domain.UnitOfWork;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.List;

public class EditFlightCommand extends AirlineCommand {

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            try {
                UnitOfWork.newCurrent();
                AirportDataMapper airportDataMapper = AirportDataMapper.getInstance();
                AirplaneDataMapper airplaneDataMapper = AirplaneDataMapper.getInstance();
                if (request.getParameter("forward") != null) {
                    int flightId = Integer.parseInt(request.getParameter("flightId"));
                    Flight flight = FlightDataMapper.getInstance().findById(flightId);
                    request.setAttribute("flight", flight);
                    forward("/editFlight.jsp");
                    return null;
                }
                int flightId = Integer.parseInt(request.getParameter("flightId"));
                String flightCode = request.getParameter("flightCode");
                String flightDate = request.getParameter("flightDate");
                String flightTime = request.getParameter("flightTime");
                Airport destination = airportDataMapper.findById(Integer.parseInt(request.getParameter("destination")));
                Airport source = airportDataMapper.findById(Integer.parseInt(request.getParameter("source")));
                Airplane airplane = airplaneDataMapper.findById(Integer.parseInt(request.getParameter("airplane")));
                Flight flight = null;
                flight = FlightDataMapper.getInstance().findById(flightId);
                flight.setCode(flightCode);
                flight.setDate(flightDate);
                flight.setTime(flightTime);
                flight.setSource(source);
                flight.setDestination(destination);
                flight.setAirplane(airplane);
                UnitOfWork.getCurrent().commit();
            } catch (Exception e) {
                error(e);
            }
            return null;
        });
        response.sendRedirect("fourAces?command=GetFlight");
    }
}

