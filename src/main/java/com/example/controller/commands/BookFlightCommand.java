package com.example.controller.commands;

import com.example.controller.BookingController;
import com.example.datasource.CustomerDataMapper;
import com.example.domain.Customer;
import com.example.domain.Flight;
import com.example.exception.TRSException;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.List;

public class BookFlightCommand extends CustomerCommand {
    @Override
    public void processGet() throws ServletException, IOException {
    }

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            int flightId = Integer.parseInt(request.getParameter("flightId"));
            String type = request.getParameter("type");
            try {
                Customer customer = getCurrentUser();
                int customerId = customer.getId();
                switch (type) {
                    case "go":
                        BookingController.getInstance().bookGoFlight(customerId, flightId);
                        List<Flight> returnFlights = BookingController.getInstance().getReturnFlights(flightId);
                        request.setAttribute("returnFlights", returnFlights);
                        forward("/returnFlight.jsp");
                        break;
                    case "return":
                        BookingController.getInstance().bookReturnFlight(customerId, flightId);
                        forward("/addPassenger.jsp");
                        break;
                    default:
                        throw new TRSException("Invalid Flight Type");
                }
            } catch (Exception e) {
                error(e);
            }
            return null;
        });
        forward("/viewFlight.jsp");
    }

}
