package com.example.four_aces.controller.commands;

import com.example.servlet.commands.FrontCommand;
import com.example.four_aces.domain.Customer;

import javax.servlet.ServletException;
import java.io.IOException;

public class CustomerCommand extends FrontCommand {
    @Override
    public void processPost() throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Customer customer = new Customer(1, username, password);
        request.setAttribute("customer", customer);
        forward("/home.jsp");
    }

    @Override
    public void processGet() throws ServletException, IOException {
    }
}
