package com.example.controller.commands;

import com.example.domain.Admin;
import com.example.domain.Airline;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ManageAirlineCommand extends FrontCommand{

    @Override
    public void processGet() throws ServletException, IOException {
        try {
            ViewAirline();
        } catch (Exception e) {
            error(e, "admin.jsp");
        }
    }

    @Override
    public void processPost() throws ServletException, IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", request.getParameter("id"));
        params.put("pending", Boolean.toString(request.getParameter("pending") != null));
        try{
            Admin.getAdmin().approveAirlineRegistration(params);
            ViewAirline();
        }catch (Exception e){
            error(e, "admin.jsp");
        }
    }

    private void ViewAirline() throws ServletException, IOException, SQLException{
        ArrayList<Airline> airlines = Admin.getAdmin().viewAirlines();
        request.setAttribute("airlines",airlines);
        request.setAttribute("command", "view airline");
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }
}
