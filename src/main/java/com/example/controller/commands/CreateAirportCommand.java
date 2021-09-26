package com.example.controller.commands;


import com.example.authentication.AAEnforcer;
import com.example.domain.Admin;
import com.example.domain.Airline;
import com.example.domain.Airport;
import com.example.domain.UnitOfWork;
import com.example.exception.AccessDeniedException;


import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;

public class CreateAirportCommand extends AdminCommand {

    @Override
    public void processGet() throws ServletException, IOException {
        forward("/createAirport.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Admin>) () -> {
            String code = request.getParameter("referenceCode");
            String address = request.getParameter("address");
            new Airport(null, code, address);
            return null;
        });
        try {
            UnitOfWork.getInstance().commit();
        }catch (Exception e) {
            error(e);
        }
        response.sendRedirect("fourAces?command=GetAirport");
    }
}




