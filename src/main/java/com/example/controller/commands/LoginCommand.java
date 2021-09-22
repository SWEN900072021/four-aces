package com.example.controller.commands;

import com.example.datasource.AirlineDataMapper;
import com.example.datasource.CustomerDataMapper;
import com.example.domain.Airline;
import com.example.domain.Customer;
import com.example.domain.User;
import com.example.exception.TRSException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginCommand extends FrontCommand {

    @Override
    public void processGet() throws ServletException, IOException {

    }

    @Override
    public void processPost() throws ServletException, IOException {
        User user;
        HashMap<String, String> params = new HashMap<>();
        params.put("email",request.getParameter("email"));
        String password = request.getParameter("password");
        try {

            switch (request.getParameter("type")) {
                case "airline":
                    ArrayList<Airline> airlines = AirlineDataMapper.getInstance().find(params);
                    if (airlines.size() == 0) throw new TRSException("Register first");
                    if( airlines.size() != 1 ) throw new TRSException("Multiple Airline with same email address found in the system");
                    user = airlines.get(0);
                    user.login(password);
                    request.setAttribute("user", user);
                    forward("/airline.jsp");
                    break;
                case "customer":
                    ArrayList<Customer> customers = CustomerDataMapper.getInstance().find(params);
                    if (customers.size() == 0) throw new TRSException("Register first");
                    if( customers.size() != 1 ) throw new TRSException("Multiple customers with same email address found in the system");
                    user = customers.get(0);
                    user.login(password);
                    request.setAttribute("user", user);
                    forward("/customer.jsp");
                    break;
                default:
                    throw new TRSException("Invalid User Type");
            }
        }catch (Exception e){
            error(e, "/login.jsp");
        }
    }
}
