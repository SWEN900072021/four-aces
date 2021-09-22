package com.example.controller.commands;

import com.example.controller.AuthenticationController;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class AdminLoginCommand extends FrontCommand{


    @Override
    public void processGet() throws ServletException, IOException {

    }

    @Override
    public void processPost() throws ServletException, IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", request.getParameter("username"));
        params.put("password", request.getParameter("password"));
        params.put("type","admin");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        int result = new AuthenticationController().login(params);
        if (result > 0) {
            writer.println(String.format("<h2>%s Login successfully.</h2>", "Admin"));
        } else if (result == AuthenticationController.LOGIN_FAIL_NO_USER_FOUND)
            writer.println("<h2>No Admin account has been created</h2>");
        else writer.println("<h2>Login Failed</h2>");
    }
}
