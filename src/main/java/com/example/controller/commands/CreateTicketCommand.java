package com.example.controller.commands;

import com.example.datasource.AirplaneDataMapper;
import com.example.domain.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CreateTicketCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        int airlineId = Integer.parseInt(request.getParameter("airlineId"));
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        int airplaneId = Integer.parseInt(request.getParameter("airplaneId"));
        AirplaneDataMapper airplaneDataMapper = AirplaneDataMapper.getInstance();
        try {
            Airplane airplane = airplaneDataMapper.findById(airplaneId);
            List<Seat> seats = airplane.getSeats();
            // Create a ticket for each seat
            for (int i = 0; i < seats.size(); i++) {
                Seat seat = seats.get(i);
                String seatClass = seat.getSeatClass();
                String seatNumber = seat.getSeatNumber();
                new Ticket(null, 100.0, flightId, seatClass, seatNumber);
                UnitOfWork.getInstance().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        forward("/fourAces?command=GetFlight&airlineId=" + airlineId);
    }

    @Override
    public void processPost() throws ServletException, IOException {
    }
}
