package com.example.controller.commands;

import com.example.domain.Passenger;


import javax.servlet.ServletException;
import java.io.IOException;

public class PassengerCommand extends FrontCommand {

    @Override
    public void processGet() throws ServletException, IOException {

    }

    @Override
    public void processPost() throws ServletException, IOException {
        String passenger_firstName = request.getParameter("passenger_firstName");
        String passenger_lastName = request.getParameter("passenger_lastName");
        String identificationType = request.getParameter("identificationType");
        String identificationNumber = request.getParameter("identificationNumber");
        Passenger passenger = new Passenger(passenger_firstName,passenger_lastName,identificationType,identificationNumber);
    }

}
