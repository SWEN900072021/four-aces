package com.example.controller.commands;


import com.example.datasource.AirportDataMapper;
import com.example.domain.Admin;
import com.example.domain.Airport;


import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.List;

public class GetAirportCommand extends AdminCommand {

    @Override
    public void processGet() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            try {
                List<Airport> airports = AirportDataMapper.getInstance().getAll();
                request.setAttribute("airports", airports);
            } catch (Exception e) {
                error(e);
            }
            return null;
        });
        forward("/airports.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {

    }
}

