package com.example.controller.commands;


import com.example.domain.Airport;
import com.example.domain.UnitOfWork;


import javax.servlet.ServletException;
import java.io.IOException;

public class CreateAirportCommand extends FrontCommand {

    @Override
    public void processGet() throws ServletException, IOException {

    }

    @Override
    public void processPost() throws ServletException, IOException {

        String referenceCode = request.getParameter("referenceCode");
        String address = request.getParameter("address");

        Airport airport = new Airport(referenceCode,address);
        UnitOfWork.getInstance().commit();
        forward("/home.jsp");
    }
}




