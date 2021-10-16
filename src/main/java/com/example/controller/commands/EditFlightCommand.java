package com.example.controller.commands;

import com.example.concurrency.LockManager;
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

public class EditFlightCommand extends AirlineCommand {

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            try {
                AirportDataMapper airportDataMapper = AirportDataMapper.getInstance();
                AirplaneDataMapper airplaneDataMapper = AirplaneDataMapper.getInstance();
                String httpSessionId = request.getSession(true).getId();
                if (request.getParameter("forward") != null) {
                    int flightId = Integer.parseInt(request.getParameter("flightId"));
                    LockManager.getInstance().acquireLock("flight-" + flightId, httpSessionId);
                    Flight flight = FlightDataMapper.getInstance().findById(flightId);
                    request.setAttribute("flight", flight);
                    forward("/editFlight.jsp");
                    return null;
                }
                UnitOfWork.newCurrent();
                int flightId = Integer.parseInt(request.getParameter("flightId"));
                String flightCode = request.getParameter("flightCode");
                String flightDate = request.getParameter("flightDate");
                String flightTime = request.getParameter("flightTime");
                Airport destination = airportDataMapper.findById(Integer.parseInt(request.getParameter("destination")));
                Airport source = airportDataMapper.findById(Integer.parseInt(request.getParameter("source")));
                Flight flight = null;
                flight = FlightDataMapper.getInstance().findById(flightId);
                flight.setCode(flightCode);
                flight.setDate(flightDate);
                flight.setTime(flightTime);
                flight.setSource(source);
                flight.setDestination(destination);
                UnitOfWork.getCurrent().commit();

                LockManager.getInstance().releaseLock("flight-" + flightId, httpSessionId);
            } catch (Exception e) {
                e.printStackTrace();
                request.getSession().setAttribute("error", "Unable to edit flight. " + e);
            }
            return null;
        });
        response.sendRedirect("fourAces?command=GetFlight");
    }
}

