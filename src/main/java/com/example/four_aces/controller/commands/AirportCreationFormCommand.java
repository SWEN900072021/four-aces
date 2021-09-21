package com.example.four_aces.controller.commands;

import javax.servlet.ServletException;
import java.io.IOException;

public class AirportCreationFormCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        forward("/createFlights.jsp");
    }
}
