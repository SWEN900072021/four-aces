package com.example.controller.commands;

import com.example.concurrency.LockManager;
import com.example.domain.*;
import com.example.exception.TRSException;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.concurrent.atomic.AtomicReference;

public class SubmitBookingCommand extends CustomerCommand {

    @Override
    public void processGet() throws ServletException, IOException {
        AtomicReference<String> target = new AtomicReference<>("/confirmBooking.jsp");
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            try {
                String errMsg = (String) request.getSession().getAttribute("error");

                if( errMsg != null ){
                    throw new TRSException(errMsg);
                }
                String httpSessionId = request.getSession(true).getId();
                //UnitOfWork unitOfWork = (UnitOfWork) request.getSession().getAttribute("unitOfWork");
                BookingUnitOfWork bookingUnitOfWork = (BookingUnitOfWork) request.getSession().getAttribute("bookingUnitOfWork");
                bookingUnitOfWork.commit();
                for (Ticket ticket : bookingUnitOfWork.getAllTickets()) {
                    LockManager.getInstance().releaseLock("ticket-" + ticket.getId(), new LockManager.LockObserver(request.getSession()) {
                        @Override
                        public void update() {
                        }
                    });
                }
                bookingUnitOfWork.rollback();
            } catch (Exception e) {
                error(e);
                target.set("/addPassenger.jsp");
            }
            return null;
        });
        forward(target.get());
    }

    @Override
    public void processPost() throws ServletException, IOException {
        forward("/confirmBooking.jsp");
    }
}
