package com.example.controller.commands;

import com.example.datasource.ReservationDataMapper;
import com.example.domain.Flight;
import com.example.domain.Reservation;

import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PreviousFlightsCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        LocalDateTime now = LocalDateTime.now();
        HashMap<String, String> params = new HashMap<>();
        params.put("customer_id", Integer.toString(customerId));
        params.put("submitted", Boolean.toString(true));
        try {
            List<Reservation> reservations = ReservationDataMapper.getInstance().find(params);
            List<Flight> flights = new ArrayList<Flight>();
            for (Reservation reservation : reservations) {
                flights.addAll(reservation.getFlights());
            }
            List<Flight> previousFlights = new ArrayList<Flight>();
            for (Flight flight : flights) {
                LocalDateTime flightDateTime = flight.getDateTime();
                if (flightDateTime.isBefore(now)) {
                    previousFlights.add(flight);
                }
            }
            request.setAttribute("flights", previousFlights);
            forward("/upcomingFlights.jsp?customerId=" + customerId);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "You've got no previous flight");
            forward("/customer.jsp?customerId=" + customerId);
        }
    }
    @Override
    public void processPost() throws ServletException, IOException {
    }
}
