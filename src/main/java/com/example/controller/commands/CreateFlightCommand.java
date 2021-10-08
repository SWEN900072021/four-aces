package com.example.controller.commands;

import com.example.datasource.AirplaneDataMapper;
import com.example.datasource.AirportDataMapper;
import com.example.domain.*;

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
                AirportDataMapper airportDataMapper = AirportDataMapper.getInstance();
                AirplaneDataMapper airplaneDataMapper = AirplaneDataMapper.getInstance();

                Airline airline = getCurrentUser();
                String flightCode = request.getParameter("flightCode");
                String flightDate = request.getParameter("flightDate");
                String flightTime = request.getParameter("flightTime");
                Airport source = airportDataMapper.findById(Integer.parseInt(request.getParameter("source")));
                Airport destination = airportDataMapper.findById(Integer.parseInt(request.getParameter("destination")));
                Airplane airplane = airplaneDataMapper.findById(Integer.parseInt(request.getParameter("airplane")));
                List<Airport> stopovers = new ArrayList<>();
                if (request.getParameter("stopover1") != "") {
                    Airport stopover1 = airportDataMapper.findById(Integer.parseInt(request.getParameter("stopover1")));
                    stopovers.add(stopover1);
                }
                if (request.getParameter("stopover2") != "") {
                    Airport stopover2 = airportDataMapper.findById(Integer.parseInt(request.getParameter("stopover2")));
                    stopovers.add(stopover2);
                }
                if (request.getParameter("stopover3") != "") {
                    Airport stopover3 = airportDataMapper.findById(Integer.parseInt(request.getParameter("stopover3")));
                    stopovers.add(stopover3);
                }
                new Flight(null, flightCode, flightDate, flightTime, source, destination, airline, airplane, stopovers);
                UnitOfWork.getCurrent().commit();
                forward("/airline.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
