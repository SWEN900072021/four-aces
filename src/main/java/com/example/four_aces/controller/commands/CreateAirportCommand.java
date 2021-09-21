package com.example.four_aces.controller.commands;



import com.example.four_aces.datasource.AirportMapper;

import javax.servlet.ServletException;
import java.io.IOException;

public class CreateAirportCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String referenceCode = request.getParameter("referenceCode");
        String address= request.getParameter("address");
        AirportMapper dbConnection = new AirportMapper();
        dbConnection.addAirport(referenceCode,address);
        forward("/home.jsp");
    }
}
