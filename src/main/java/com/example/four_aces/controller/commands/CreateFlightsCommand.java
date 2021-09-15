package main.java.com.example.four_aces.controller.commands;

import main.java.com.example.four_aces.domain.Flight;

import javax.servlet.ServletException;
import java.io.IOException;

public class CreateFlightsCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String flightCode = request.getParameter("flightCode");
        String flightDate= request.getParameter("flightDate");
        String flightTime = request.getParameter("flightTime");
        Flight flight = new Flight(1, "QF170", "2021/09/09", "15:00pm");
        request.setAttribute("flight", flight);
        forward("/home.jsp");
    }
}
