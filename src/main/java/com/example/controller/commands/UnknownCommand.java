package com.example.controller.commands;

import com.example.datasource.AdminDataMapper;
import com.example.domain.User;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;

public class UnknownCommand extends FrontCommand {
    public void process() throws ServletException, IOException {
        forward("/unknown.jsp");
    }

    @Override
    public void processGet() throws ServletException, IOException {
        process();
    }

    @Override
    public void processPost() throws ServletException, IOException {
        process();
    }

    @Override
    protected User getCurrentUser() throws Exception {
        String username = aaEnforcer.getSubject().getPrincipals().iterator().next().getName();
        HashMap<String,String> params = new HashMap<>();
        params.put("email", username);
        return AdminDataMapper.getInstance().find(params).get(0);
    }
}