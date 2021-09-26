package com.example.controller.commands;

import com.example.datasource.ReservationDataMapper;
import com.example.domain.Customer;
import com.example.domain.Flight;
import com.example.domain.Reservation;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UpcomingFlightsCommand extends CustomerCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        LocalDateTime now = LocalDateTime.now();
        HashMap<String, String> params = new HashMap<>();
        params.put("submitted", Boolean.toString(true));
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {try {
            Customer customer = getCurrentUser();
            params.put("customer_id", Integer.toString(customer.getId()));
            List<Reservation> reservations = ReservationDataMapper.getInstance().find(params);
            List<Flight> flights = new ArrayList<Flight>();
            for (Reservation reservation : reservations) {
                flights.addAll(reservation.getFlights());
            }
            List<Flight> upcomingFlights = new ArrayList<Flight>();
            for (Flight flight : flights) {
                LocalDateTime flightDateTime = flight.getDateTime();
                if (now.isBefore(flightDateTime)) {
                    upcomingFlights.add(flight);
                }
            }
            request.setAttribute("flights", upcomingFlights);
            forward("/upcomingFlights.jsp");
        } catch (Exception e) {
            request.setAttribute("error", "You've got no upcoming flight");
        }
            return null;
        });
        forward("/customer.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
    }



}
