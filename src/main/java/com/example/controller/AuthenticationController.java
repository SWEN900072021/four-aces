package com.example.controller;

import com.example.domain.Admin;
import com.example.domain.Airline;
import com.example.domain.Customer;
import com.example.domain.User;
import com.example.exception.TRSException;

import java.sql.SQLException;
import java.util.HashMap;

public class AuthenticationController {

    public User login(HashMap<String, String> params) throws TRSException, SQLException {
        User user = null;
        switch (params.remove("type")){
            case "airline":
                user = new Airline().login(params);
                break;
            case "customer":
                user = new Customer().login(params);
                break;
            case "admin":
                user = Admin.getAdmin().login(params);
                break;
            default:
                throw new TRSException("Invalid User Type");
        }
        return user;
    }

    public User register(HashMap<String, String> params) throws TRSException, SQLException {
        User user = null;
        switch (params.remove("type")) {
            case "admin":
                user = Admin.getAdmin().register(params);
                break;
            case "airline":
                user = new Airline().register(params);
                break;
            case "customer":
                user = new Customer().register(params);
                break;
            default:
                throw new TRSException("Invalid User Type");
        }
        return user;
    }
}
