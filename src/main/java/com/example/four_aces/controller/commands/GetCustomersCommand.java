package main.java.com.example.four_aces.controller.commands;

import main.java.com.example.four_aces.datasource.CustomerMapper;
import main.java.com.example.four_aces.domain.Customer;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class GetCustomersCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        CustomerMapper dbConnection = new CustomerMapper();
        List<Customer> customers = dbConnection.getAllUsers();
        request.setAttribute("customers", customers);
        forward("/customers.jsp");
    }
}

