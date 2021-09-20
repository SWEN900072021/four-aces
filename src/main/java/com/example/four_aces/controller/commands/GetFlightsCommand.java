package main.java.com.example.four_aces.controller.commands;

import main.java.com.example.four_aces.datasource.FlightMapper;
import main.java.com.example.four_aces.domain.Flight;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class GetFlightsCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        List<Flight> flights = FlightMapper.getInstance().getAll();
        if (flights.size() > 0) {
            request.setAttribute("flights", flights);
        }
        forward("/flights.jsp");
    }
}

