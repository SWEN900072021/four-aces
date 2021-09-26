package com.example.controller.commands;

import com.example.authentication.AAEnforcer;
import com.example.authentication.AirlinePrincipal;
import com.example.datasource.AdminDataMapper;
import com.example.datasource.AirlineDataMapper;
import com.example.domain.Airline;
import com.example.domain.User;
import com.example.exception.AccessDeniedException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;

public class AirlineCommand extends FrontCommand {

    public AirlineCommand() {
        super();
        this.principal = AirlinePrincipal.class;
    }

    @Override
    public void processGet() throws ServletException, IOException {
        forward("/airline.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {

    }

    @Override
    protected Airline getCurrentUser() throws Exception {
        String username = aaEnforcer.getSubject().getPrincipals().iterator().next().getName();
        HashMap<String,String> params = new HashMap<>();
        params.put("email", username);
        return AirlineDataMapper.getInstance().find(params).get(0);
    }
}
