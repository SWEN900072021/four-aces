package com.example.controller.commands;

import com.example.domain.Admin;
import com.example.domain.Airline;
import com.example.domain.Customer;
import com.example.domain.User;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageUserCommand extends FrontCommand{


    @Override
    public void processGet() throws ServletException, IOException {
        try {
            ArrayList<Airline> airlines = Admin.getAdmin().viewAirlines();
            ArrayList<Customer> customers = Admin.getAdmin().viewCustomers();
            ArrayList<User> users = new ArrayList<>();
            users.addAll(airlines);
            users.addAll(customers);
            request.setAttribute("user",users);
            request.setAttribute("view", "user");
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        } catch (Exception e) {
            error(e, "admin.jsp");
        }
    }

    @Override
    public void processPost() throws ServletException, IOException {

    }

}
