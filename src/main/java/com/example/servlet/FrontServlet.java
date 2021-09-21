package com.example.servlet;

import com.example.servlet.commands.FrontCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "FrontServlet", value = "/fourAces")
public class FrontServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FrontCommand command = getCommand(req.getParameter("command"));
        command.init(getServletContext(), req, resp);
        command.processGet();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FrontCommand command = getCommand(req.getParameter("command"));
        command.init(getServletContext(), req, resp);
        command.processPost();
    }

    private FrontCommand getCommand(String command){
        FrontCommand frontCommand = null;
        String commandClassName = "com.example.servlet.commands." + command + "Command";
        try {
            Class<?> commandClass = Class.forName(commandClassName);
            frontCommand = (FrontCommand) commandClass.getConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return frontCommand;
    }
}
