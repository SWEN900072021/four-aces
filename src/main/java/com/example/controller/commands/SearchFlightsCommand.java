package com.example.controller.commands;

import com.example.datasource.AirportDataMapper;
import com.example.datasource.FlightDataMapper;
import com.example.domain.Airport;
import com.example.domain.Flight;
import com.example.exception.NoRecordFoundException;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchFlightsCommand extends CustomerCommand {

    @Override
    public void processGet() throws ServletException, IOException {
        forward("/searchFlightsResult.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            HashMap<String, String> params = new HashMap<>();
            HashMap<String, String> originAirportParams = new HashMap<>();
            originAirportParams.put("address", request.getParameter("origin"));
            HashMap<String, String> destinationAirportParams = new HashMap<>();
            destinationAirportParams.put("address", request.getParameter("destination"));

            try {
                List<Airport> originAirports = AirportDataMapper.getInstance().find(originAirportParams);
                List<Airport> destinationAirports = AirportDataMapper.getInstance().find(destinationAirportParams);
                if (originAirports.size() > 0 && destinationAirports.size() > 0) {
                    params.put("origin", Integer.toString(originAirports.get(0).getId()));
                    params.put("destination", Integer.toString(destinationAirports.get(0).getId()));
                    params.put("flight_date", request.getParameter("date"));
                    ArrayList<Flight> flights = FlightDataMapper.getInstance().find(params);
                    List<Flight> availableFLights = new ArrayList<>();
                    for (Flight flight : flights) {
                        if (flight.getAvailableTickets().size() > 0) {
                            availableFLights.add(flight);
                        }
                    }
                    if (availableFLights.size() == 0) {
                        System.out.println("NO FLIGHT FOUND");
                        request.setAttribute("error", "Flight not found. Please try searching another flight");
                        forward("/customer.jsp");
                    } else {
                        request.setAttribute("flights", availableFLights);
                        forward("/searchFlightsResult.jsp");
                    }
                } else {
                    System.out.println("NO AIRPORT FOUND");
                    request.setAttribute("error", "Flight not found. Please try searching another flight");
                    forward("/customer.jsp");
                }
            } catch (NoRecordFoundException e) {
                e.printStackTrace();
                request.setAttribute("error", "Flight not found. Please try searching another flight");
                try {
                    forward("/customer.jsp");
                } catch (ServletException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } catch ( ServletException | SQLException | IOException e ) {
                e.printStackTrace();
            }
            return null;
        });

    }

}
