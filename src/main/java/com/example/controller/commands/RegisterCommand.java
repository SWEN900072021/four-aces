package com.example.controller.commands;


import com.example.domain.*;

import javax.servlet.ServletException;
import java.io.IOException;

public class RegisterCommand extends FrontCommand{

    @Override
    public void processGet() throws ServletException, IOException {
        forward("/register.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user;
        switch (request.getParameter("type")){
            case "Airline":
                user = new Airline(null, username, email, password);
                break;
            case "Customer":
                user = new Customer(null, username, email, password);
                break;
            case "Admin":
                user = new Admin(null, username, password);
                break;
            default:
                request.setAttribute("error", "Wrong User Type");
                forward("/register.jsp");
        }
        try{
            UnitOfWork.getInstance().commit();
            response.sendRedirect("login.jsp");
        } catch (Exception e) {
            error(e);
            forward("/register.jsp");
        }
    }

    @Override
    protected User getCurrentUser() throws Exception {
        return null;
    }

}
