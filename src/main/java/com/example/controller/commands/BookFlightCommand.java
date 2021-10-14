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
import java.util.HashMap;
import java.util.List;

public class BookFlightCommand extends CustomerCommand {
    @Override
    public void processGet() throws ServletException, IOException {
    }

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            try {
                int flightId = Integer.parseInt(request.getParameter("flightId"));
                Flight flight = FlightDataMapper.getInstance().findById(flightId);
                String type = request.getParameter("type");
                Customer customer = getCurrentUser();
                int customerId = customer.getId();
                switch (type) {
                    case "go":
                        //UnitOfWork unitOfWork = UnitOfWork.getCurrent();
                        BookingUnitOfWork bookingUnitOfWork = new BookingUnitOfWork();
                        Reservation reservation = new Reservation(null, customer);
                        reservation.bookGoFlight(flight);
                        bookingUnitOfWork.registerReservation(reservation);
                        List<Flight> returnFlights = BookingController.getInstance().getReturnFlights(flight);
                        request.setAttribute("returnFlights", returnFlights);
                        //request.getSession().setAttribute("unitOfWork", unitOfWork);
                        request.getSession().setAttribute("bookingUnitOfWork", bookingUnitOfWork);
                        forward("/returnFlight.jsp");
                        break;
                    case "return":
                        BookingUnitOfWork newUnitOfWork = (BookingUnitOfWork) request.getSession().getAttribute("bookingUnitOfWork");
                        //UnitOfWork newUnitOfWork = (UnitOfWork) request.getSession().getAttribute("unitOfWork");
                        //Reservation createdReservation = (Reservation) newUnitOfWork.getNewObjectOf("Reservation");
                        Reservation createdReservation = newUnitOfWork.getReservation();
                        createdReservation.bookReturnFlight(flight);
                        //request.getSession().setAttribute("unitOfWork", newUnitOfWork);
                        // TODO: passing a list of existing passengers for users to choose
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
