package com.example.controller.commands;

import com.example.controller.AuthenticationController;
import com.example.exception.TRSException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
        try{
            new AuthenticationController().login(params);
            response.sendRedirect("admin.jsp");
        } catch (TRSException | SQLException e) {
            response.getWriter().write(e.getMessage());
        }
    }
}
