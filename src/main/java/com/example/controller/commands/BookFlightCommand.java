package com.example.controller.commands;

import com.example.controller.BookingController;
import com.example.datasource.CustomerDataMapper;
import com.example.datasource.FlightDataMapper;
import com.example.domain.Customer;
import com.example.domain.Flight;
import com.example.domain.Reservation;
import com.example.domain.UnitOfWork;
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
                        UnitOfWork.newCurrent();
                        Reservation reservation = new Reservation(null, customer);
                        reservation.bookGoFlight(flight);
                        List<Flight> returnFlights = BookingController.getInstance().getReturnFlights(flight);
                        request.setAttribute("returnFlights", returnFlights);
                        forward("/returnFlight.jsp");
                        break;
                    case "return":
                        UnitOfWork unitOfWork = UnitOfWork.getCurrent();
                        Reservation createdReservation = (Reservation) unitOfWork.getNewObjectOf("Reservation");
                        createdReservation.bookReturnFlight(flight);
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
