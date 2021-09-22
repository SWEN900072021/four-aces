package com.example.controller;

import com.example.controller.commands.FrontCommand;
import com.example.controller.commands.UnknownCommand;



import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FrontServlet", value = "/fourAces")
public class FrontServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FrontCommand command = getCommand(request);
        command.init(getServletContext(), request, response);
        command.processGet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FrontCommand command = getCommand(request);
        command.init(getServletContext(), request, response);
        command.processPost();
    }

    private FrontCommand getCommand(HttpServletRequest request) {
        try {
            return (FrontCommand) getCommandClass(request).newInstance();
        } catch (Exception e) {
            return new UnknownCommand();
        }
    }

    private Class getCommandClass(HttpServletRequest request) {
        Class result;
        String commandClassName =
            "com.example.controller.commands." + (String) request.getParameter("command") + "Command";
        try {
            result = Class.forName(commandClassName);
        } catch (ClassNotFoundException e) {
            result = UnknownCommand.class;
        }
        return result;
    }
}
