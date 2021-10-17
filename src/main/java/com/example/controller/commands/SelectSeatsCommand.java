package com.example.controller.commands;

import com.example.concurrency.LockManager;
import com.example.controller.BookingController;
import com.example.datasource.CustomerDataMapper;
import com.example.datasource.PassengerDataMapper;
import com.example.datasource.TicketDataMapper;
import com.example.domain.*;
import com.example.exception.ConcurrencyException;
import com.example.exception.TRSException;

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
                    String errMsg = (String) request.getSession().getAttribute("error");
                    if (errMsg != null) {
                        throw new TRSException(errMsg);
                    }
                    String httpSessionId = request.getSession(true).getId();
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
                    boolean returning = reservation.isReturning();
                    try {
                        if (ticket.getPassenger() != null) {
                            throw new ConcurrencyException("");
                        }
                        LockManager.getInstance().acquireLock("ticket-" + ticketId, new LockManager.LockObserver(request.getSession()) {
                            @Override
                            public void update() {
                                this.getSession().setAttribute("error", "This flight is being edited by the Airline, Please wait. ");
                            }
                        });
                        bookingUnitOfWork.registerTicket(ticket);
                        if (type.equals("go") && returning) {
                            List<Ticket> tickets = BookingController.getInstance().getAvailableTickets(reservation.getReturnFlight());
                            request.setAttribute("tickets", tickets);
                            request.setAttribute("type", "return");
                            request.setAttribute("returning", returning);
                            //request.setAttribute("passenger",passenger);
                            forward("/chooseSeats.jsp");
                        } else {
                            forward("/addPassenger.jsp");
                        }
                    } catch (ConcurrencyException e) {
                        request.setAttribute("type", type);
                        List<Ticket> tickets;
                        if (type.equals("go")) {
                            tickets = BookingController.getInstance().getAvailableTickets(reservation.getGoFlight());
                        } else {
                            tickets = BookingController.getInstance().getAvailableTickets(reservation.getReturnFlight());
                        }
                        request.setAttribute("tickets", tickets);
                        request.setAttribute("returning", returning);
                        request.setAttribute("error", "The seat you selected has been taken. Please select another seat.");
                        forward("/chooseSeats.jsp");
                    }

                } catch (Exception e) {
                    error(e);
                    try {
                        forward("/chooseSeats.jsp");
                    } catch (ServletException | IOException ex) {
                        ex.printStackTrace();
                    }
                }
                return null;
            }
        });
    }
}
