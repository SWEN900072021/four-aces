package com.example.controller.commands;

import com.example.datasource.AirlineDataMapper;
import com.example.datasource.AirportDataMapper;
import com.example.domain.Admin;
import com.example.domain.Airline;
import com.example.domain.UnitOfWork;
import com.example.exception.TRSException;

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
        try{
            Airline airline = AirlineDataMapper.getInstance().findById(Integer.parseInt(request.getParameter("id")));
            airline.setPending(request.getParameter("pending")!=null);
            UnitOfWork.getInstance().commit();
            ViewAirline();
        }catch (Exception e){
            error(e, "/admin.jsp");
        }
    }

    private void ViewAirline() throws Exception{
        ArrayList<Airline> airlines = AirlineDataMapper.getInstance().getAll();
        if(airlines.size() == 0){
            throw new TRSException("No Airline found in database");
        }
        request.setAttribute("airline",airlines);
        request.setAttribute("command", "view airline");
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }
}
