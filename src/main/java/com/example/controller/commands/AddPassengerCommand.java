package com.example.controller.commands;

import com.example.controller.BookingController;
import com.example.datasource.*;
import com.example.domain.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
        BookingController bookingController = BookingController.getInstance();
        Passenger passenger = new Passenger(null, firstName, lastName, idType, idNum);
        HashMap<String, String> params = new HashMap<>();
        params.put("passenger_firstname", firstName);
        params.put("passenger_lastname", lastName);
        params.put("identificationtype", idType);
        params.put("identificationnumber", idNum);

        try {
            UnitOfWork.getInstance().commit();
            Passenger savedPassenger = PassengerDataMapper.getInstance().find(params).get(0);
            List<Ticket> tickets = bookingController.getAvailableGoTickets(customerId);
            request.setAttribute("tickets", tickets);
            request.setAttribute("type", "go");
            request.setAttribute("passengerId", savedPassenger.getId());
            request.setAttribute("returning", bookingController.isReturning(customerId));
            forward("/chooseSeats.jsp?customerId=" + customerId + "&passengerId=" + savedPassenger.getId());
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            forward("/unknown.jsp");
        }


    }
}
