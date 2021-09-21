package com.example.four_aces.controller.commands;

/*import com.example.four_aces.datasource.FlightMapper;
import main.java.com.example.four_aces.domain.Flight;*/
import com.example.four_aces.datasource.AirportMapper;
import com.example.four_aces.domain.Airport;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class GetAirportCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        AirportMapper dbConnection = new AirportMapper();
        List<Airport> airports = dbConnection.getAllAirport();
        request.setAttribute("airports", airports);
        forward("/airports.jsp");
    }
}

