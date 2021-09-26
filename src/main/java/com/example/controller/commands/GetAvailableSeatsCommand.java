package com.example.controller.commands;

import com.example.controller.BookingController;
import com.example.datasource.CustomerDataMapper;
import com.example.datasource.FlightDataMapper;
import com.example.datasource.TicketDataMapper;
import com.example.domain.Customer;
import com.example.domain.Flight;
import com.example.domain.Passenger;
import com.example.domain.Ticket;
import com.example.exception.TRSException;

import javax.servlet.ServletException;
import java.awt.print.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetAvailableSeatsCommand extends CustomerCommand {
    @Override
    public void processGet() throws ServletException, IOException {
//        int customerId = Integer.parseInt(request.getParameter("customerId"));
//        String type = request.getParameter("type");
//        BookingController bookingController = BookingController.getInstance();
//
//        try {
//            Customer customer = CustomerDataMapper.getInstance().findById(customerId);
//            List<Passenger> passengers = bookingController.getPassengers(customerId);
//            int numPassengers = passengers.size();
//            Flight flight = null;
//            List<Ticket> tickets = null;
//
//            switch (type) {
//                case "go":
//                    flight = bookingController.getFlight(customerId);
//                    tickets = TicketDataMapper.getInstance().getAll(flight.getId(), true);
//                    request.setAttribute("numPassengers", numPassengers);
//                    request.setAttribute("tickets", tickets);
//                    request.setAttribute("type", type);
//                case "return":
//                    flight = bookingController.getReturnFlight(customerId);
//                    tickets = TicketDataMapper.getInstance().getAll(flight.getId(), true);
//                    request.setAttribute("numPassengers", numPassengers);
//                    request.setAttribute("tickets", tickets);
//                    request.setAttribute("type", type);
//                default:
//                    throw new TRSException("Invalid Flight Type");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        forward("/chooseSeats.jsp?customerId=" + customerId);
    }

    @Override
    public void processPost() throws ServletException, IOException {

    }

}
