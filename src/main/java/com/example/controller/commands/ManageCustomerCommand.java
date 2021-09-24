package com.example.controller.commands;

import com.example.datasource.CustomerDataMapper;
import com.example.domain.Customer;
import com.example.exception.TRSException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

public class ManageCustomerCommand extends FrontCommand{

    @Override
    public void processGet() throws ServletException, IOException {
        try {
            ArrayList<Customer> customers = CustomerDataMapper.getInstance().getAll();
            if (customers.size() > 0) {
                request.setAttribute("user", customers);
                request.setAttribute("view", "customer");
            }
            else throw new TRSException("No customer found in the system");
            forward("/admin.jsp");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
    }

    @Override
    public void processPost() throws ServletException, IOException {

    }
}
