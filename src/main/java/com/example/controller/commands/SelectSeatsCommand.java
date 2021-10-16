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
    }

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                try {
                    String type = request.getParameter("type");
                    int ticketId = Integer.parseInt(request.getParameter("select"));
                    Passenger passenger = (Passenger) request.getAttribute("passenger");
                    Customer customer = getCurrentUser();
                    //UnitOfWork unitOfWork = (UnitOfWork) request.getSession().getAttribute("unitOfWork");
                    BookingUnitOfWork bookingUnitOfWork = (BookingUnitOfWork) request.getSession().getAttribute("bookingUnitOfWork");
                    //Passenger passenger = bookingUnitOfWork.getCurrentPassenger();
                    //Reservation reservation = (Reservation) unitOfWork.getNewObjectOf("Reservation");
                    Reservation reservation = bookingUnitOfWork.getReservation();
                    Ticket ticket = TicketDataMapper.getInstance().findById(ticketId);
                    bookingUnitOfWork.registerTicket(ticket);
//                    ticket.setPassenger(passenger);
//                    reservation.bookTicket(passenger, ticket);
                    boolean returning = reservation.isReturning();
                    if (type.equals("go") && returning) {
                        request.setAttribute("type", type);
                        List<Ticket> tickets = BookingController.getInstance().getAvailableTickets(reservation.getReturnFlight());
                        request.setAttribute("tickets", tickets);
                        request.setAttribute("type", "return");
                        request.setAttribute("returning", returning);
                        //request.setAttribute("passenger",passenger);
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
