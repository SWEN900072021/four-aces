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
import java.util.ArrayList;
import java.util.List;

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
                String stopover1 = request.getParameter("stopover1");
                String stopover2 = request.getParameter("stopover2");
                String stopover3 = request.getParameter("stopover3");
                List<Airport> stopovers = new ArrayList<>();
                if (stopover1 != "") {
                    Airport stopover1Airport = airportDataMapper.findById(Integer.parseInt(stopover1));
                    stopovers.add(stopover1Airport);
                }
                if (stopover2 != "") {
                    Airport stopover2Airport = airportDataMapper.findById(Integer.parseInt(stopover2));
                    stopovers.add(stopover2Airport);
                }
                if (stopover3 != "") {
                    Airport stopover3Airport = airportDataMapper.findById(Integer.parseInt(stopover3));
                    stopovers.add(stopover3Airport);
                }

                Flight flight = null;
                flight = FlightDataMapper.getInstance().findById(flightId);
                flight.setCode(flightCode);
                flight.setDate(flightDate);
                flight.setTime(flightTime);
                flight.setStopovers(stopovers);
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

