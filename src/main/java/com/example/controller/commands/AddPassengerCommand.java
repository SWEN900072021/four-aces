package com.example.controller.commands;

import com.example.controller.BookingController;
import com.example.datasource.AbstractDataMapper;
import com.example.datasource.CustomerDataMapper;
import com.example.domain.*;

import javax.servlet.ServletException;
import java.io.IOException;

public class AddPassengerCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
    }

    @Override
    public void processPost() throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String idType = request.getParameter("idType");
        String idNum = request.getParameter("idNum");

        Passenger passenger = new Passenger(firstName, lastName, idType, idNum);
        try {
            Customer customer = CustomerDataMapper.getInstance().findById(customerId);
            UnitOfWork.getInstance().commit();
            BookingController.getInstance().addPassenger(customer, passenger);

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
        forward("/addPassenger.jsp?customerId=" + customerId);
    }
}
