package com.example.controller.commands;

import com.example.datasource.AirportDataMapper;
import com.example.domain.Airline;
import com.example.domain.Airport;
import com.example.domain.UnitOfWork;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

//TODO
public class CreateFlightCommand extends AirlineCommand {

    @Override
    public void processGet() throws ServletException, IOException {
        try {
            List<Airport> airports = AirportDataMapper.getInstance().getAll();
            request.setAttribute("airports", airports);
            forward("/createFlight.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            try {
                UnitOfWork.newCurrent();
                Airline airline = getCurrentUser();
                String flightCode = request.getParameter("flightCode");
                String flightDate = request.getParameter("flightDate");
                String flightTime = request.getParameter("flightTime");
                int source = Integer.parseInt(request.getParameter("source"));
                int destination = Integer.parseInt(request.getParameter("destination"));
                int airplaneId = Integer.parseInt(request.getParameter("airplane"));
                List<Integer> stopovers = new ArrayList<>();
                if (request.getParameter("stopover1") != "") {
                    stopovers.add(Integer.parseInt(request.getParameter("stopover1")));
                }
                if (request.getParameter("stopover2") != "") {
                    stopovers.add(Integer.parseInt(request.getParameter("stopover2")));
                }
                if (request.getParameter("stopover3") != "") {
                    stopovers.add(Integer.parseInt(request.getParameter("stopover3")));
                }
                airline.createFlight(flightCode, flightDate, flightTime, source, destination, airplaneId, stopovers);
                UnitOfWork.getCurrent().commit();
                forward("/airline.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
