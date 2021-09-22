package com.example.controller.commands;
import com.example.datasource.CustomerMapper;
import com.example.domain.Customer;




import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
public class GetCustomerCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        List<Customer> customers = CustomerMapper.getInstance().getAll();
        if (customers.size() > 0) {
            request.setAttribute("customer", customers);
        }
        forward("/customer.jsp");
    }
}
