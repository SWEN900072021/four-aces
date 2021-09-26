package com.example.controller;

import com.example.controller.commands.FrontCommand;
import com.example.controller.commands.UnknownCommand;
import com.example.exception.AccessDeniedException;
import com.example.exception.TRSException;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "FrontServlet", value = "/fourAces")
public class FrontServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FrontCommand command = getCommand(request.getParameter("command"));
        command.init(getServletContext(), request, response);
        command.checkAccess();
        command.processGet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FrontCommand command = getCommand(request.getParameter("command"));
        command.init(getServletContext(), request, response);
        command.checkAccess();
        command.processPost();
    }

    private FrontCommand getCommand(String command) {
        FrontCommand frontCommand = null;
        String commandClassName = "com.example.controller.commands." + command + "Command";
        try {
            Class<?> commandClass = Class.forName(commandClassName);
            frontCommand = (FrontCommand) commandClass.getConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            frontCommand = new UnknownCommand();
        }
        return frontCommand;
    }
}
