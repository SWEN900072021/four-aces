package com.example.controller.commands;

import com.example.datasource.ReservationDataMapper;
import com.example.domain.Flight;
import com.example.domain.Reservation;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UpcomingFlightsCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        HashMap<String, String> params = new HashMap<>();
        params.put("customer_id", Integer.toString(customerId));
        params.put("submitted", Boolean.toString(true));
        try {
            List<Reservation> reservations = ReservationDataMapper.getInstance().find(params);
            List<Flight> flights = new ArrayList<Flight>();
            for (Reservation reservation : reservations) {
                flights.addAll(reservation.getFlights());
            }
            request.setAttribute("flights", flights);
            forward("/upcomingFlights.jsp?customerId=" + customerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processPost() throws ServletException, IOException {

    }
}
