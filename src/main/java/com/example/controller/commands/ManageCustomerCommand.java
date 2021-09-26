package com.example.controller.commands;

import com.example.datasource.CustomerDataMapper;
import com.example.domain.Customer;
import com.example.exception.TRSException;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.ArrayList;

public class ManageCustomerCommand extends AdminCommand{

    @Override
    public void processGet() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                try {
                    ArrayList<Customer> customers = CustomerDataMapper.getInstance().getAll();
                    if (customers.size() > 0) {
                        request.setAttribute("users", customers);
                        request.setAttribute("view", "customer");
                    }
                    else throw new TRSException("No customer found in the system");
                } catch (Exception e) {
                    error(e);
                }
                return null;
            }
        });
        forward("/admin.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {

    }
}
