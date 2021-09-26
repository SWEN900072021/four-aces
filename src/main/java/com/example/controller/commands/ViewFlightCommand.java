package com.example.controller.commands;

import com.example.datasource.FlightDataMapper;
import com.example.domain.Flight;
import com.example.exception.TRSException;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;

public class ViewFlightCommand extends CustomerCommand {

    @Override
    public void processGet() throws ServletException, IOException {
    }

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(this.aaEnforcer.getSubject(), new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                int flightId = Integer.parseInt(request.getParameter("flightId"));
                String type = request.getParameter("type");
                try {
                    Flight flight = FlightDataMapper.getInstance().findById(flightId);
                    if (flight != null) {
                        request.setAttribute("flight", flight);
                        request.setAttribute("type", type);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        forward("/viewFlight.jsp");

    }

}
