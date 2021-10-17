package com.example.controller.commands;

import com.example.datasource.AirplaneDataMapper;
import com.example.datasource.FlightDataMapper;
import com.example.domain.*;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.List;
public class CreateTicketCommand extends AirlineCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        forward("/createTicket.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            try {
                int flightId = Integer.parseInt(request.getParameter("flightId"));
                int airplaneId = Integer.parseInt(request.getParameter("airplaneId"));

                Flight flight = FlightDataMapper.getInstance().findById(flightId);
                List<Ticket> tickets = flight.getAvailableTickets();
                // Check if tickets have been created
                if (tickets.size() > 0) {
                    request.getSession().setAttribute("error", "Tickets have been created. ");
                    return null;
                }

                UnitOfWork.newCurrent();
                AirplaneDataMapper airplaneDataMapper = AirplaneDataMapper.getInstance();
                Airplane airplane = airplaneDataMapper.findById(airplaneId);
                List<Seat> seats = airplane.getSeats();
                // Create a ticket for each seat
                for (Seat seat : seats) {
                    String seatClass = seat.getSeatClass();
                    String seatNumber = seat.getSeatNumber();
                    new Ticket(null, 100.0, flightId, seatClass, seatNumber);
                }
                UnitOfWork.getCurrent().commit();
            }
            catch (Exception e) {
                error(e);
            }
            return null;
        });
        response.sendRedirect("fourAces?command=GetFlight");
    }
}
