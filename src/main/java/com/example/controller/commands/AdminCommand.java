package com.example.controller.commands;

import com.example.authentication.AAEnforcer;
import com.example.authentication.AdminPrincipal;
import com.example.authentication.UserPrincipal;
import com.example.domain.Admin;
import com.example.exception.AccessDeniedException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Set;

public class AdminCommand extends FrontCommand{

    public AdminCommand(){
        super();
        this.principal = AdminPrincipal.class;
    }

    @Override
    public void processGet() throws ServletException, IOException {
        forward("/admin.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {

    }
}
