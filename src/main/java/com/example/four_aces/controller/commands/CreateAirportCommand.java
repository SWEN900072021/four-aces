package com.example.four_aces.controller.commands;


import com.example.four_aces.domain.Airport;
import com.example.four_aces.domain.UnitOfWork;

import javax.servlet.ServletException;
import java.io.IOException;

public class CreateAirportCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String referenceCode = request.getParameter("referenceCode");
        String address = request.getParameter("address");

        Airport airport = new Airport(referenceCode,address);
        UnitOfWork.getInstance().commit();
        forward("/home.jsp");
    }
}




