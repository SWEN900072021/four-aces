package controller.commands;

import domain.Customer;

import javax.servlet.ServletException;
import java.io.IOException;

public class CustomerCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Customer customer = new Customer(1, username, password);
        request.setAttribute("customer", customer);
        request.setAttribute("command", "Customer");
        forward("/home");
    }
}
