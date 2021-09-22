package com.example.controller.commands;
import com.example.datasource.PassengerMapper;
import com.example.domain.Customer;
import com.example.domain.Passenger;


import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
public class GetPassengerCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        List<Passenger> passengers = PassengerMapper.getInstance().getAllPassengers();
        if (passengers.size() > 0) {
            request.setAttribute("passenger", passengers);
        }
        forward("/passenger.jsp");
    }
}