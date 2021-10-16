package com.example.controller.commands;

import com.example.concurrency.LockManager;
import com.example.controller.BookingController;
import com.example.datasource.FlightDataMapper;
import com.example.domain.*;
import com.example.exception.TRSException;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.List;

public class CancelBookingCommand extends CustomerCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            try {
                String httpSessionId = request.getSession(true).getId();
                Customer customer = getCurrentUser();
                //UnitOfWork unitOfWork = (UnitOfWork) request.getSession().getAttribute("unitOfWork");
                BookingUnitOfWork bookingUnitOfWork = (BookingUnitOfWork) request.getSession().getAttribute("bookingUnitOfWork");
                for (Ticket ticket : bookingUnitOfWork.getAllTickets()) {
                    LockManager.getInstance().releaseLock("ticket-" + ticket.getId(), httpSessionId);
                }
                bookingUnitOfWork.rollback();
                request.getSession().removeAttribute("bookingUnitOfWork");
            } catch (Exception e) {
                e.printStackTrace();
                error(e);
            }
            return null;
        });
        forward("/customer.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            try {
                Customer customer = getCurrentUser();
                //UnitOfWork unitOfWork = (UnitOfWork) request.getSession().getAttribute("unitOfWork");
                BookingUnitOfWork bookingUnitOfWork = (BookingUnitOfWork) request.getSession().getAttribute("bookingUnitOfWork");
                bookingUnitOfWork.rollback();
            } catch (Exception e) {
                error(e);
            }
            return null;
        });
        forward("/customer.jsp");
    }
}
