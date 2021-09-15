package main.java.com.example.four_aces.controller.commands;

import main.java.com.example.four_aces.domain.Customer;
import javax.servlet.ServletException;
import java.io.IOException;

public class FlightCreationFormCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        forward("/createFlights.jsp");
    }
}
