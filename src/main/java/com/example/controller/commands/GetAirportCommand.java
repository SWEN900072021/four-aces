package com.example.controller.commands;


import com.example.datasource.AirportMapper;
import com.example.domain.Airport;



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

