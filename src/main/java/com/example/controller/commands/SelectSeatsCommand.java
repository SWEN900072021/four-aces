package com.example.controller.commands;

import com.example.controller.BookingController;
import com.example.datasource.CustomerDataMapper;
import com.example.domain.Customer;
import com.example.domain.Passenger;
import com.example.domain.UnitOfWork;

import javax.servlet.ServletException;
import java.io.IOException;

public class SelectSeatsCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
    }

    @Override
    public void processPost() throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String type = request.getParameter("type");
        String[] selectedTicketIds = request.getParameterValues("selected");
        BookingController bookingController = BookingController.getInstance();
        try {
            Customer customer = CustomerDataMapper.getInstance().findById(customerId);
            bookingController.bookSeats(customer, selectedTicketIds);
            if (type.equals("go") && bookingController.isReturning(customer)) {
               request.setAttribute("type", type);
               forward( "/chooseSeats.jsp?customerId="+ customerId);
            } else {
                forward("/confirmBooking.jsp?customerId=" + customerId);
            }

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
    }
}
