package com.example.controller.commands;

import com.example.authentication.AAEnforcer;
import com.example.authentication.AirlinePrincipal;
import com.example.exception.AccessDeniedException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.security.Principal;

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
}
