package com.example.controller.commands;

import com.example.datasource.AirportDataMapper;
import com.example.datasource.FlightDataMapper;
import com.example.domain.Airport;
import com.example.domain.Flight;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchFlightsCommand extends FrontCommand {

    @Override
    public void processGet() throws ServletException, IOException {

    }

    @Override
    public void processPost() throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        HashMap<String, String> params = new HashMap<>();
        HashMap<String, String> originAirportParams = new HashMap<>();
        originAirportParams.put("address", request.getParameter("origin"));
        HashMap<String, String> destinationAirportParams = new HashMap<>();
        destinationAirportParams.put("address", request.getParameter("destination"));

        ArrayList<Flight> flights = null;
        try {
            List<Airport> originAirports = AirportDataMapper.getInstance().find(originAirportParams);
            List<Airport> destinationAirports = AirportDataMapper.getInstance().find(destinationAirportParams);
            if (originAirports.size() > 0 && destinationAirports.size() > 0) {
                params.put("origin", Integer.toString(originAirports.get(0).getId()));
                params.put("destination", Integer.toString(destinationAirports.get(0).getId()));
                params.put("date", request.getParameter("date"));
                flights = FlightDataMapper.getInstance().find(params);
                List<Flight> availableFLights = new ArrayList<>();
                for (Flight flight : flights) {
                    if (flight.getAvailableTickets().size() > 0) {
                        availableFLights.add(flight);
                    }
                }
                if (availableFLights.size() == 0) {
                    request.setAttribute("error", "Flight not found. Please try searching another flight");
                    forward("/customer.jsp?customerId=" + customerId);
                } else {
                    request.setAttribute("flights", availableFLights);
                }
            } else {
                request.setAttribute("flights", new ArrayList<Flight>());
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Flight not found. Please try searching another flight");
            forward("/customer.jsp?customerId=" + customerId);
        }

    }

}
