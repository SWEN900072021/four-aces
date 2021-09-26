package com.example.controller.commands;

import com.example.authentication.AAEnforcer;
import com.example.authentication.AdminPrincipal;
import com.example.authentication.AirlinePrincipal;
import com.example.authentication.UserPrincipal;
import com.example.datasource.CustomerDataMapper;
import com.example.domain.Customer;
import com.example.domain.User;
import com.example.exception.AccessDeniedException;
import com.example.exception.TRSException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Set;

public abstract class FrontCommand {
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected Class<? extends UserPrincipal> principal;
    protected AAEnforcer aaEnforcer;

    public void init(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse
    ) {
        this.context = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
        this.aaEnforcer = (AAEnforcer) request.getSession().getAttribute("auth");
    }

    public abstract void processGet() throws ServletException, IOException;

    public abstract void processPost() throws ServletException, IOException;

    protected void forward(String target) throws ServletException, IOException {
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }

    protected void error(Exception e){
        e.printStackTrace();
        request.setAttribute("error", e.getClass() + e.getMessage());
    }

    public void checkAccess() throws IOException, ServletException {
        if (this.getClass().equals(RegisterCommand.class) || this.getClass().equals((LoginCommand.class))) return;
        System.out.println(this.getClass());
        try{
            if( aaEnforcer == null || !aaEnforcer.isLoggedIn() ){
                throw new TRSException("No Authentication Token");
            }else {
                for (Principal p : aaEnforcer.getSubject().getPrincipals()) {
                    if (!p.getClass().equals(this.principal)) {
                        System.out.println(p.getClass());
                        System.out.println(principal.getClass());
                        throw new AccessDeniedException();
                    }
                }
            }
        } catch (AccessDeniedException | TRSException e) {
            error(e);
            forward(getLoginPage());
        }
    }

    protected String getLoginPage(){
        if (AdminPrincipal.class.equals(this.principal)) {
            return "/adminLogin.jsp";
        }else {
            return "/login.jsp";
        }
    }

    protected abstract User getCurrentUser() throws Exception;
}
