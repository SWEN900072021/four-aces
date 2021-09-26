package com.example.controller.commands;

import com.example.controller.BookingController;
import com.example.datasource.CustomerDataMapper;
import com.example.datasource.PassengerDataMapper;
import com.example.datasource.TicketDataMapper;
import com.example.domain.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class SelectSeatsCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
    }

    @Override
    public void processPost() throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        int passengerId = Integer.parseInt(request.getParameter("passengerId"));
        String type = request.getParameter("type");
//        String[] selectedTicketIds = request.getParameterValues("selected");
//        int ticketId = Integer.parseInt(selectedTicketIds[0]);
        int ticketId = Integer.parseInt(request.getParameter("select"));
        BookingController bookingController = BookingController.getInstance();
        try {
            bookingController.bookTicket(customerId, passengerId, ticketId, type);
            boolean returning = bookingController.isReturning(customerId);
            if (type.equals("go") && returning) {
                request.setAttribute("type", type);
                Flight flight = bookingController.getReturnFlight(customerId);
                List<Ticket> tickets = TicketDataMapper.getInstance().getAll(flight.getId(), true);
                request.setAttribute("tickets", tickets);
                request.setAttribute("type", "return");
                request.setAttribute("returning", returning);
                //request.setAttribute("passengerId", passengerId);
                forward("/chooseSeats.jsp?customerId=" + customerId + "&passengerId=" + passengerId);
            } else {
                forward("/addPassenger.jsp?customerId=" + customerId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
        }
    }
}
