package com.example.controller.commands;

import com.example.datasource.AdminDataMapper;
import com.example.datasource.DataMapper;
import com.example.domain.Admin;
import com.example.exception.TRSException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

public class AdminLoginCommand extends FrontCommand{


    @Override
    public void processGet() throws ServletException, IOException {

    }

    @Override
    public void processPost() throws ServletException, IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", request.getParameter("username"));
        try{
            Admin admin = AdminDataMapper.getInstance().find(params).get(0);
            admin.login(request.getParameter("password"));
            forward("/admin.jsp");
        } catch (Exception e) {
            error(e, "/adminLogin.jsp");
        }
    }
}
