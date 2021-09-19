package main.java.com.example.four_aces.controller.commands;

import main.java.com.example.four_aces.datasource.FlightMapper;
import main.java.com.example.four_aces.domain.Flight;
import main.java.com.example.four_aces.domain.UnitOfWork;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class CreateFlightCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String flightCode = request.getParameter("flightCode");
        String flightDate= request.getParameter("flightDate");
        String flightTime = request.getParameter("flightTime");
        FlightMapper flightMapper = new FlightMapper();
        flightMapper.insert(new Flight(10, flightCode, flightDate, flightTime));
        UnitOfWork.getInstance().commit();
        forward("/home.jsp");
    }
}
