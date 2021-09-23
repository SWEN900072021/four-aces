package com.example.controller.commands;


import com.example.domain.Airport;
import com.example.domain.UnitOfWork;
import com.example.exception.TRSException;


import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

public class CreateAirportCommand extends FrontCommand {

    @Override
    public void processGet() throws ServletException, IOException {
    }

    @Override
    public void processPost() throws ServletException, IOException {
        String referenceCode = request.getParameter("referenceCode");
        String address = request.getParameter("address");

        new Airport(null, referenceCode,address);
        try {
            UnitOfWork.getInstance().commit();
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
        forward("/admin.jsp");
    }
}




