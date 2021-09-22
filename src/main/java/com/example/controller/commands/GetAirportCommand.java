package com.example.controller.commands;


import com.example.datasource.AirportDataMapper;
import com.example.domain.Airport;



import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class GetAirportCommand extends FrontCommand {

    @Override
    public void processGet() throws ServletException, IOException {
        try {
            List<Airport> airports = AirportDataMapper.getInstance().getAll();
            request.setAttribute("airports", airports);
        } catch (Exception e) {
            e.printStackTrace();
        }
        forward("/airports.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {

    }
}

