package main.java.com.example.four_aces.controller.commands;

import main.java.com.example.four_aces.datasource.FlightMapper;
import main.java.com.example.four_aces.domain.Flight;
import main.java.com.example.four_aces.domain.UnitOfWork;

import javax.servlet.ServletException;
import java.io.IOException;

public class EditFlightCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String flightCode = request.getParameter("flightCode");
        String flightDate= request.getParameter("flightDate");
        String flightTime = request.getParameter("flightTime");
        FlightMapper flightMapper = new FlightMapper();
        Flight flight = flightMapper.findById(id);
        flight.setCode(flightCode);
        flight.setDate(flightDate);
        flight.setTime(flightTime);
        UnitOfWork.getInstance().commit();
    }
}

