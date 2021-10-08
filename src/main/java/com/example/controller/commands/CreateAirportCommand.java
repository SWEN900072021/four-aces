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
            try {
                String code = request.getParameter("referenceCode");
                String address = request.getParameter("address");
                UnitOfWork.newCurrent();
                new Airport(null, code, address);
                UnitOfWork.getCurrent().commit();
                response.sendRedirect("fourAces?command=GetAirport");
            } catch (Exception e) {
                error(e);
            }
            return null;
        });
    }
}




