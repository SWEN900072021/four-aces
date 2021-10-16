package com.example.controller.commands;

import com.example.controller.BookingController;
import com.example.datasource.FlightDataMapper;
import com.example.domain.BookingUnitOfWork;
import com.example.domain.Customer;
import com.example.domain.Flight;
import com.example.domain.Reservation;
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
                Customer customer = getCurrentUser();
                //UnitOfWork unitOfWork = (UnitOfWork) request.getSession().getAttribute("unitOfWork");
                BookingUnitOfWork bookingUnitOfWork = (BookingUnitOfWork) request.getSession().getAttribute("bookingUnitOfWork");
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
