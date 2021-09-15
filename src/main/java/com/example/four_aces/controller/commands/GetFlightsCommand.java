package main.java.com.example.four_aces.controller.commands;

import main.java.com.example.four_aces.datasource.FlightMapper;
import main.java.com.example.four_aces.domain.Flight;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class GetFlightsCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        FlightMapper dbConnection = new FlightMapper();
        List<Flight> flights = dbConnection.getAllFlights();
        request.setAttribute("flights", flights);
        forward("/flights.jsp");
    }
}

