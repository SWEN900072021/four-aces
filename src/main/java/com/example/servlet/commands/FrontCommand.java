package com.example.servlet.commands;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class FrontCommand {
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public void init(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse
    ) {
        this.context = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
    }

    public abstract void processGet() throws ServletException, IOException;

    public abstract void processPost() throws ServletException, IOException;

    protected void forward(String target) throws ServletException, IOException {
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }

    protected void error(Exception e, String target) throws ServletException, IOException {
        request.setAttribute("error", e);
        request.getRequestDispatcher(target).forward(request, response);
    }

}
