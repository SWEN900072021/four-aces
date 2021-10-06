package com.example.controller.commands;

import com.example.datasource.CustomerDataMapper;
import com.example.datasource.PassengerDataMapper;
import com.example.datasource.ReservationDataMapper;
import com.example.domain.Customer;
import com.example.domain.Flight;
import com.example.domain.Passenger;
import com.example.domain.Reservation;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewCustomerCommand extends AirlineCommand {

    @Override
    public void processPost() throws ServletException, IOException {
        Subject.doAs(aaEnforcer.getSubject(), new PrivilegedAction<Flight>() {
            @Override
            public Flight run() {
                String flightId = request.getParameter("flightId");
                HashMap<String, String> params = new HashMap<>();
                params.put("go_flight", flightId);
                params.put("return_flight", flightId);
                try {
                    ArrayList<Reservation> reservations = ReservationDataMapper.getInstance().find(params);
                    ArrayList<Customer> customers = new ArrayList<Customer>();
                    for (Reservation reservation : reservations) {
                        Customer customer = CustomerDataMapper.getInstance().findById(reservation.getCustomerId());
                        customers.add(customer);
                    }
                    request.setAttribute("customers", customers);
                } catch (Exception e) {
                    error(e);
                }
                return null;
            }
        });
        forward("/viewCustomer.jsp");
    }
}

