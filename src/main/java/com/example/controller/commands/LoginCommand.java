package com.example.controller.commands;

import com.example.authentication.AAEnforcer;
import com.example.domain.User;
import com.example.exception.TRSException;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import java.io.IOException;

@SuppressWarnings("unchecked")
public class LoginCommand extends FrontCommand {

    @Override
    public void processGet() throws ServletException, IOException {
    }

    @Override
    public void processPost() throws ServletException, IOException {
        String target = "";
        String etarget = "";
        switch (request.getParameter("type")){
            case "admin":
                target = "fourAces?command=Admin";
                etarget = "/adminLogin.jsp";
                break;
            case "airline":
                target = "fourAces?command=Airline";
                etarget = "/login.jsp";
                break;
            case "customer":
                target = "fourAces?command=Customer";
                etarget = "/login.jsp";
                break;
            default:
                error(new TRSException("Invalid User Type"));
                forward(etarget);
        }
        try {
            AAEnforcer authn = new AAEnforcer(request);
            authn.login();
            if( authn.isLoggedIn() )
                request.getSession().setAttribute("auth", authn);
            else throw new TRSException("Login Failed");
            response.sendRedirect(target);
        } catch (LoginException | TRSException e) {
            error(e);
            forward(etarget);
        }
    }

    @Override
    protected User getCurrentUser() throws Exception {
        return null;
    }

}
