package com.example.controller.commands;

import com.example.authentication.AAEnforcer;
import com.example.datasource.AirlineDataMapper;
import com.example.datasource.AirportDataMapper;
import com.example.domain.Admin;
import com.example.domain.Airline;
import com.example.domain.UnitOfWork;
import com.example.exception.TRSException;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ManageAirlineCommand extends AdminCommand {

    @Override
    public void processGet() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Airline>) () -> {
            try {
                ViewAirline();
            } catch (Exception e) {
                error(e);
            }
            return null;
        });
        forward("/admin.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), new PrivilegedAction<Airline>() {
            @Override
            public Airline run() {
                Airline airline = null;
                try {
                    airline = AirlineDataMapper.getInstance().findById(Integer.parseInt(request.getParameter("id")));
                    airline.setPending(request.getParameter("pending") != null);
                    UnitOfWork.getInstance().commit();
                    ViewAirline();
                } catch (Exception e) {
                    error(e);
                }
                return airline;
            }
        });
        forward("/admin.jsp");
    }

    private void ViewAirline() throws Exception {
        ArrayList<Airline> airlines = AirlineDataMapper.getInstance().getAll();
        if (airlines.size() == 0) {
            throw new TRSException("No Airline found in database");
        }
        request.setAttribute("users", airlines);
        request.setAttribute("view", "airline");
    }
}
