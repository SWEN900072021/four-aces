package main.java.com.example.four_aces.controller.commands;

import main.java.com.example.four_aces.datasource.FlightMapper;
import main.java.com.example.four_aces.domain.Flight;
import main.java.com.example.four_aces.domain.UnitOfWork;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteFlightCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        System.out.println("delete");
        int id = Integer.parseInt(request.getParameter("id"));
        FlightMapper flightMapper = new FlightMapper();
        flightMapper.deleteById(id);
        forward("/frontServlet?command=GetFlights");
    }
}
