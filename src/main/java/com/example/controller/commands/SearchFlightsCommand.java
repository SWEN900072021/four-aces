package com.example.controller.commands;

import com.example.datasource.FlightDataMapper;
import com.example.domain.Flight;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchFlightsCommand extends CustomerCommand {

    @Override
    public void processGet() throws ServletException, IOException {
        forward("/searchFlightsResult.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            HashMap<String, String> params = new HashMap<>();
//            params.put("date",request.getParameter("date"));
//            params.put("time",request.getParameter("time"));
            params.put("date","2021-09-28");
            params.put("time","18:40");
            ArrayList<Flight> flights = new ArrayList<>();
            try {
                flights = FlightDataMapper.getInstance().find(params);
                request.setAttribute("flights", flights);
            } catch (Exception e) {
                request.setAttribute("flights", flights);
                error(e);
            }
            return null;
        });
        forward("/searchFlightsResult.jsp");
    }

}
