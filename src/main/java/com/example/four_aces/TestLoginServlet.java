package com.example.four_aces;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TestLoginServlet", value = "/testlogin")
public class TestLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        System.out.println("Hello from GET method in LoginServlet");
        String user = request.getParameter("userName");
        String pass = request.getParameter("passWord");
        PrintWriter writer = response.getWriter();
        writer.println("<h3> Hello from Get "+user+ " " +pass+ "</h3>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        System.out.println("Hello from Post method in LoginServlet");
        String user = request.getParameter("userName");
        String pass = request.getParameter("passWord");
        PrintWriter writer = response.getWriter();
        writer.println("<h3> Hello! We are team Four Aces! </h3>");
        writer.println("<h3> Your username is: " + user + "</h3>");
        writer.println("<h3> Your password is: " + pass + "</h3>");
    }
}

