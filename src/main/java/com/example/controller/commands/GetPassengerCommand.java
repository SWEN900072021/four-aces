package com.example.controller.commands;
import com.example.datasource.PassengerDataMapper;
import com.example.domain.Passenger;


import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
public class GetPassengerCommand extends FrontCommand {

    @Override
    public void processGet() throws ServletException, IOException {
        List<Passenger> passengers = null;
        try {
            passengers = PassengerDataMapper.getInstance().getAll();
            if (passengers.size() > 0) {
                request.setAttribute("passenger", passengers);
            }
        } catch (Exception e) {
            // TODO: send error message
            e.printStackTrace();
        }
        forward("/passenger.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {

    }
}