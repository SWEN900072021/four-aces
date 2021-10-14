package com.example.controller.commands;

import com.example.controller.BookingController;
import com.example.datasource.CustomerDataMapper;
import com.example.datasource.FlightDataMapper;
import com.example.domain.*;
import com.example.exception.TRSException;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.List;

public class SubmitBookingCommand extends CustomerCommand {

    @Override
    public void processGet() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            try {
                Customer customer = getCurrentUser();
                //UnitOfWork unitOfWork = (UnitOfWork) request.getSession().getAttribute("unitOfWork");
                BookingUnitOfWork bookingUnitOfWork = (BookingUnitOfWork) request.getSession().getAttribute("bookingUnitOfWork");
                bookingUnitOfWork.commit();
            } catch (Exception e) {
                error(e);
            }
            return null;
        });
        forward("/confirmBooking.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        forward("/confirmBooking.jsp");
    }
}
