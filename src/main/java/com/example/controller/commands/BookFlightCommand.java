package com.example.controller.commands;

import com.example.controller.BookingController;
import com.example.datasource.CustomerDataMapper;
import com.example.datasource.FlightDataMapper;
import com.example.domain.Booking;
import com.example.domain.Customer;
import com.example.domain.Flight;
import com.example.exception.TRSException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookFlightCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        String type = request.getParameter("type");
        FlightDataMapper flightDataMapper = FlightDataMapper.getInstance();
        CustomerDataMapper customerDataMapper = CustomerDataMapper.getInstance();
        BookingController bookingController = BookingController.getInstance();

        try {
            Flight flight = flightDataMapper.findById(flightId);
            Customer customer = customerDataMapper.findById(customerId);
            switch (type) {
                case "go":
                    if (flight != null && customer != null) {
                        bookingController.bookFlight(customerId, flight);

                        List<Flight> returnFlights = BookingController.getInstance().getReturnFlights(flight);
                        request.setAttribute("returnFlights", returnFlights);
                    }
                    forward("/returnFlight.jsp?customerId="+customerId);
                    break;
                case "return":
                    if (flight != null && customer != null) {
                        bookingController.bookReturnFlight(customerId, flight);
                    }
                    forward("/addPassenger.jsp?customerId="+customerId);
                    break;
                default:
                    throw new TRSException("Invalid Flight Type");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processPost() throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        FlightDataMapper flightDataMapper = FlightDataMapper.getInstance();
        CustomerDataMapper customerDataMapper = CustomerDataMapper.getInstance();
        try {
            Flight flight = flightDataMapper.findById(flightId);
            Customer customer = customerDataMapper.findById(customerId);

            if (flight != null && customer != null) {
                BookingController.getInstance().bookFlight(customerId, flight);
                List<Flight> returnFlights = BookingController.getInstance().getReturnFlights(flight);
                request.setAttribute("returnFlights", returnFlights);
            }
            forward("/returnFlight.jsp?customerId="+customerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
