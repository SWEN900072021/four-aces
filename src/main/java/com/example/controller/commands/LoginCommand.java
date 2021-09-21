package com.example.controller.commands;

import com.example.controller.AuthenticationController;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
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
        PrintWriter writer = response.getWriter();
        int result = new AuthenticationController().login(params);
        if (result > 0) {
            if (request.getParameter("type").equals("customer")) {
                forward("/customer.jsp");
            } else {
                forward("/airline.jsp");
            }
        } else if (result == AuthenticationController.LOGIN_FAIL_NO_USER_FOUND)
            writer.println("<h2>Register first</h2>");
        else writer.println("<h2>Login Failed</h2>");
    }
}
