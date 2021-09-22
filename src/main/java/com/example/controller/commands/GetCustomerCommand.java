package com.example.controller.commands;
import com.example.datasource.CustomerDataMapper;
import com.example.domain.Customer;




import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
public class GetCustomerCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        try {
            ArrayList<Customer> customers = new CustomerDataMapper().find("*","");
            if (customers.size() > 0) {
                request.setAttribute("customer", customers);
            }
            forward("/customer.jsp");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
    }

    @Override
    public void processPost() throws ServletException, IOException {

    }
}
