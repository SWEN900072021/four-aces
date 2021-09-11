//package controllers;
//
//import domain.Customer;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@WebServlet(name = "LoginController", value = "/login")
//public class LoginController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//        System.out.println("Hello from GET method in LoginController");
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        Customer customer = new Customer(1, username, password);
//
//        request.setAttribute("customer", customer);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//        System.out.println("Hello from Post method in LoginController");
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        Customer customer = new Customer(1, username, password);
//
//        request.setAttribute("customer", customer);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
//        dispatcher.forward(request, response);
//    }
//}
//
