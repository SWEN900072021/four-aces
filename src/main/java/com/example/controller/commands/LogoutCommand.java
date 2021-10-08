package com.example.controller.commands;

import com.example.datasource.AdminDataMapper;
import com.example.domain.User;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;

public class LogoutCommand extends FrontCommand{

    @Override
    public void processGet() throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("index.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {
    }

    @Override
    protected User getCurrentUser() throws Exception {
        return null;
    }
}
