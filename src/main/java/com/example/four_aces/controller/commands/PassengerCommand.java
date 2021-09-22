package com.example.four_aces.controller.commands;

import com.example.four_aces.domain.Passenger;

import javax.servlet.ServletException;
import java.io.IOException;

public class PassengerCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String passenger_id = request.getParameter("passenger_id");
        String passenger_firstName = request.getParameter("passenger_firstName");
        String passenger_lastName = request.getParameter("passenger_lastName");
        String identificationType = request.getParameter("identificationType");
        String identificationNumber = request.getParameter("identificationNumber");
        Passenger passenger = new Passenger(passenger_id,passenger_firstName,passenger_lastName,identificationType,identificationNumber);
  }
}
