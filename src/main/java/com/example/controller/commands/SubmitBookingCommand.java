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
import java.util.List;

public class SubmitBookingCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        try {
            BookingController.getInstance().submitBooking(customerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        forward("/confirmBooking.jsp");

    }

    @Override
    public void processPost() throws ServletException, IOException {
        forward("/confirmBooking.jsp");
    }
}
