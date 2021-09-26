package com.example.controller.commands;

import com.example.authentication.AAEnforcer;
import com.example.authentication.CustomerPrincipal;
import com.example.datasource.AdminDataMapper;
import com.example.datasource.CustomerDataMapper;
import com.example.domain.Customer;
import com.example.domain.User;
import com.example.exception.AccessDeniedException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;

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

    @Override
    protected Customer getCurrentUser() throws Exception {
        String username = aaEnforcer.getSubject().getPrincipals().iterator().next().getName();
        HashMap<String,String> params = new HashMap<>();
        params.put("email", username);
        return CustomerDataMapper.getInstance().find(params).get(0);
    }
}
