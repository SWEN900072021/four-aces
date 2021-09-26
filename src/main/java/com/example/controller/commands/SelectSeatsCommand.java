package com.example.controller.commands;

import com.example.controller.BookingController;
import com.example.datasource.CustomerDataMapper;
import com.example.datasource.PassengerDataMapper;
import com.example.datasource.TicketDataMapper;
import com.example.domain.*;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.List;

public class SelectSeatsCommand extends CustomerCommand {

    @Override
    public void processGet() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                try {
                    Customer customer = getCurrentUser();
                    List<Ticket> tickets = BookingController.getInstance().getAvailableGoTickets(customer.getId());
                    request.setAttribute("tickets", tickets);
                    request.setAttribute("type", "go");
                    request.setAttribute("returning", BookingController.getInstance().isReturning(customer.getId()));
                } catch (Exception e) {
                    error(e);
                }
                return null;
            }
        });
        forward("/chooseSeats.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        String type = request.getParameter("type");
        int passengerId = Integer.parseInt(request.getParameter("passengerId"));
        int ticketId = Integer.parseInt(request.getParameter("select"));
        Subject.doAs(aaEnforcer.getSubject(), new PrivilegedAction<Object>() {
            @Override
            public Object run() {try {
                Customer customer = getCurrentUser();
                int customerId = customer.getId();
                BookingController.getInstance().bookTicket(customerId, passengerId, ticketId, type);
                boolean returning = BookingController.getInstance().isReturning(customerId);
                if (type.equals("go") && returning) {
                    request.setAttribute("type", type);
                    List<Ticket> tickets = BookingController.getInstance().getAvailableReturnTickets(customerId);
                    request.setAttribute("tickets", tickets);
                    request.setAttribute("type", "return");
                    request.setAttribute("returning", returning);
                    request.setAttribute("passengerId",passengerId);
                    forward("/chooseSeats.jsp" );
                } else {
                    forward("/addPassenger.jsp");
                }

            } catch (Exception e) {
                error(e);
            }
                return null;
            }
        });
    }
}
