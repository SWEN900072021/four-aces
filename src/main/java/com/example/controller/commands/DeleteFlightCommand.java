package com.example.controller.commands;

import com.example.datasource.FlightDataMapper;
import com.example.domain.Airline;
import com.example.exception.AccessDeniedException;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteFlightCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
    }

    @Override
    public void processPost() throws ServletException, IOException {
        try {
            Airline airline = (Airline) request.getSession(false).getAttribute("user");
            int flightId = Integer.parseInt(request.getParameter("flightId"));
            FlightDataMapper.getInstance().deleteById(flightId);
        }catch (AccessDeniedException e){
            forward("/login.jsp");
        }
        catch (Exception e) {
            // TODO: send error message to front
            e.printStackTrace();
        }
        forward("/fourAces?command=GetFlight");

    }
}
