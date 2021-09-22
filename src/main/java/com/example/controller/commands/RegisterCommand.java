package com.example.controller.commands;

import com.example.controller.AuthenticationController;
import com.example.domain.User;
import com.example.exception.TRSException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

public class RegisterCommand extends FrontCommand{

    @Override
    public void processGet() throws ServletException, IOException {

    }

    @Override
    public void processPost() throws ServletException, IOException {
        response.setContentType("text/html");
        HashMap<String, String> params = new HashMap<>();
        params.put("email",request.getParameter("email"));
        params.put("username", request.getParameter("username"));
        params.put("password", request.getParameter("password"));
        params.put("type", request.getParameter("type"));
        try{
            User user = new AuthenticationController().register(params);
            request.setAttribute("user",user);
            response.sendRedirect("login.jsp");
        } catch (TRSException | SQLException e) {
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }
    }
}
