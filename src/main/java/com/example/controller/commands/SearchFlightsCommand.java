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
        Subject.doAs(aaEnforcer.getSubject(), new PrivilegedAction<Object>() {
            @Override
            public Object run() {
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
                    error(e);
                }
                return null;
            }
        });
        response.sendRedirect("fourAces?command=SearchFlights");
    }

}
