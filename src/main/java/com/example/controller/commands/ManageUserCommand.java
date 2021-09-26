package com.example.controller.commands;

import com.example.authentication.AAEnforcer;
import com.example.datasource.AirlineDataMapper;
import com.example.datasource.CustomerDataMapper;
import com.example.domain.*;
import com.example.exception.TRSException;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.StreamSupport;

public class ManageUserCommand extends AdminCommand {

    @Override
    public void processGet() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), (PrivilegedAction<DomainObject>) () -> {
            try {
                ArrayList<Airline> airlines = AirlineDataMapper.getInstance().getAll();
                ArrayList<Customer> customers = CustomerDataMapper.getInstance().getAll();
                ArrayList<User> users = new ArrayList<>();
                users.addAll(airlines);
                users.addAll(customers);
                request.setAttribute("users", users);
                request.setAttribute("view", "user");
            } catch (Exception e) {
                error(e);
            }
            return null;
        });
        forward("/admin.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {

    }

}
