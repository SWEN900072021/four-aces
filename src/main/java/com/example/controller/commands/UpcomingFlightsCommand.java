package com.example.controller.commands;

import com.example.controller.BookingController;
import com.example.datasource.ReservationDataMapper;
import com.example.domain.Customer;
import com.example.domain.Flight;
import com.example.domain.Reservation;
import com.example.domain.Ticket;

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
        BookingController bookingController = BookingController.getInstance();
        HashMap<String, String> params = new HashMap<>();
        HashMap<Flight, List<Ticket>> upcomingFlights = new HashMap<>();
        params.put("submitted", Boolean.toString(true));
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {try {
            Customer customer = getCurrentUser();
            params.put("customer_id", Integer.toString(customer.getId()));
            List<Reservation> reservations = ReservationDataMapper.getInstance().find(params);
            if (reservations.size() == 0) {
                System.out.println("NO UPCOMING RESERVATION");
            }
            for (Reservation reservation : reservations) {
                List<Flight> reservationFlights = reservation.getFlights();
                if (reservationFlights.size() == 0) {
                    System.out.println("NO UPCOMING RESERVATION FLIGHT");
                }
                for (Flight flight : reservationFlights) {
                    LocalDateTime flightDateTime = flight.getDateTime();
                    if (now.isBefore(flightDateTime)) {
                        List<Ticket> tickets = bookingController.getReservedTickets(reservation.getId(), flight.getId());
                        upcomingFlights.put(flight, tickets);
                    }
                }
            }
            request.setAttribute("flights", upcomingFlights);
            forward("/upcomingFlights.jsp");
        } catch (Exception e) {
            e.printStackTrace();
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
