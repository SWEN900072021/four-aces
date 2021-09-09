package com.example.four_aces.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        System.out.println("Hello from GET method in LoginController");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter writer = response.getWriter();
        writer.println("<h3> Hello from Get " + username+ " " + password + "</h3>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        System.out.println("Hello from Post method in LoginController");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter writer = response.getWriter();
        writer.println("<h3> Hello! We are team Four Aces! </h3>");
        writer.println("<h3> Your username is: " + username + "</h3>");
        writer.println("<h3> Your password is: " + password + "</h3>");
    }
}

