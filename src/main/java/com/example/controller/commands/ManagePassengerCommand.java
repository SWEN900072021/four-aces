package com.example.controller.commands;

import com.example.datasource.AirlineDataMapper;
import com.example.datasource.AirportDataMapper;
import com.example.datasource.PassengerDataMapper;
import com.example.domain.Admin;

import com.example.domain.Passenger;
import com.example.domain.UnitOfWork;
import com.example.exception.TRSException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ManagePassengerCommand extends CustomerCommand{

    @Override
    public void processGet() throws ServletException, IOException {
        try {
            ViewPassenger();
        } catch (Exception e) {
            error(e);
        }
    }

    @Override
    public void processPost() throws ServletException, IOException {
        try{
            Passenger passenger = PassengerDataMapper.getInstance().findById(Integer.parseInt(request.getParameter("id")));
            UnitOfWork.getInstance().commit();
            ViewPassenger();
        }catch (Exception e){
            error(e);
        }
    }

    private void ViewPassenger() throws Exception{
        ArrayList<Passenger> passengers = PassengerDataMapper.getInstance().getAll();
        if(passengers.size() == 0){
            throw new TRSException("No Passenger found in database");
        }
        request.setAttribute("passsenger",passengers);
        request.setAttribute("command", "view passenger");
        request.getRequestDispatcher("airline.jsp").forward(request, response);
    }
}
