package com.example.controller.commands;

import com.example.controller.BookingController;
import com.example.datasource.*;
import com.example.domain.*;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class AddPassengerCommand extends CustomerCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        forward("/addPassenger.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String idType = request.getParameter("idType");
        String idNum = request.getParameter("idNum");
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<Object>) () -> {
            try{
                new Passenger(null, firstName, lastName, idType, idNum);
                UnitOfWork.getInstance().commit();

                HashMap<String, String> params = new HashMap<>();
                params.put("passenger_firstname", firstName);
                params.put("passenger_lastname", lastName);
                params.put("identificationtype", idType);
                params.put("identificationnumber", idNum);
                Passenger savedPassenger = PassengerDataMapper.getInstance().find(params).get(0);
                response.sendRedirect("fourAces?command=SelectSeats&passengerId="+savedPassenger.getId());
            } catch (Exception e) {
                error(e);
            }
            return null;
        });
    }
}
