package com.example.servlet.commands;

import com.example.controller.AuthenticationController;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
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
        AuthenticationController authenticationController = new AuthenticationController();
        PrintWriter writer = response.getWriter();
        if (authenticationController.register(params) > 0){
            response.sendRedirect("login.jsp");
        }
        else{
            writer.println("<p>Failed</p>");
        }
    }
}
