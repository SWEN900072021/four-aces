package com.example.controller.commands;

import com.example.authentication.AAEnforcer;
import com.example.authentication.CustomerPrincipal;
import com.example.exception.AccessDeniedException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.security.Principal;

public class CustomerCommand extends FrontCommand {

    public CustomerCommand() {
        super();
        this.principal = CustomerPrincipal.class;
    }

    @Override
    public void processGet() throws ServletException, IOException {
        forward("/customer.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {

    }
}
