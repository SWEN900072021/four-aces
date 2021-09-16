package com.example.servlet;

import com.example.controller.AuthenticationController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
