package com.example.four_aces.controller.commands;
import com.example.four_aces.datasource.CustomerMapper;
import com.example.four_aces.datasource.PassengerMapper;
import com.example.four_aces.domain.Customer;
import com.example.four_aces.domain.Passenger;


import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
public class GetPassengerCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        List<Customer> passengers = PassengerMapper.getInstance().getAll();
        if (passengers.size() > 0) {
            request.setAttribute("passenger", passengers);
        }
        forward("/passenger.jsp");
    }
}