package com.example.controller.commands;

import com.example.datasource.AirplaneDataMapper;
import com.example.domain.*;
import com.example.exception.AccessDeniedException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class CreateTicketCommand extends AirlineCommand {
    @Override
    public void processGet() throws ServletException, IOException {
    }

    @Override
    public void processPost() throws ServletException, IOException {
        try {
            Airline airline = (Airline) request.getSession(false).getAttribute("user");
            int flightId = Integer.parseInt(request.getParameter("flightId"));
            int airplaneId = Integer.parseInt(request.getParameter("airplaneId"));
            AirplaneDataMapper airplaneDataMapper = AirplaneDataMapper.getInstance();
            Airplane airplane = airplaneDataMapper.findById(airplaneId);
            List<Seat> seats = airplane.getSeats();
            // Create a ticket for each seat
            for (Seat seat : seats) {
                String seatClass = seat.getSeatClass();
                String seatNumber = seat.getSeatNumber();
                new Ticket(null, 100.0, flightId, seatClass, seatNumber);
                UnitOfWork.getInstance().commit();
            }
        }
        catch (AccessDeniedException e){
            forward("/login.jsp");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        forward("/fourAces?command=GetFlight");
    }
}
