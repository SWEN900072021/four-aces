package com.example.controller.commands;

import com.example.datasource.FlightDataMapper;
import com.example.domain.Flight;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchFlightsCommand extends FrontCommand {

    @Override
    public void processGet() throws ServletException, IOException {

    }

    @Override
    public void processPost() throws ServletException, IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put("date",request.getParameter("date"));
        params.put("time",request.getParameter("time"));
        ArrayList<Flight> flights = null;
        try {
            flights = FlightDataMapper.getInstance().find(params);
            if (flights.size() > 0) {
                request.setAttribute("flights", flights);
            }
        } catch (Exception e) {
            //TODO : send error message
            e.printStackTrace();
        }
        forward("/searchFlightsResult.jsp");
    }

}
