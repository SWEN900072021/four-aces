package com.example.controller.commands;

import com.example.controller.AuthenticationController;
import com.example.domain.Airline;
import com.example.domain.Customer;
import com.example.domain.User;
import com.example.exception.TRSException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginCommand extends FrontCommand {

    @Override
    public void processGet() throws ServletException, IOException {

    }

    @Override
    public void processPost() throws ServletException, IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put("email", request.getParameter("email"));
        params.put("password", request.getParameter("password"));
        params.put("type", request.getParameter("type"));
        response.setContentType("text/html");
        try {
            User user = new AuthenticationController().login(params);
            request.setAttribute("user",user);
            if( user instanceof Airline ) {
                forward("/customer.jsp");
            }
            if( user instanceof Customer ) {
                forward("/airline.jsp");
            }
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }
}
