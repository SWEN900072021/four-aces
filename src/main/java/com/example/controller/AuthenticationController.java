package com.example.controller;

import com.example.domain.Admin;
import com.example.domain.Airline;
import com.example.domain.Customer;
import java.util.HashMap;

public class AuthenticationController {

    public final static int REG_FAIL_NO_TYPE_SELECTED = -1;
    public final static int REG_DUPLICATE_ACCOUNT = -2;
    public final static int LOGIN_FAIL_WRONG_TYPE = -1;
    public static final int LOGIN_FAIL_NO_USER_FOUND = -2;

    public int login(HashMap<String, String> params){
        switch (params.remove("type")){
            case "airline":
                return new Airline().login(params);
            case "customer":
                return new Customer().login(params);
            case "admin":
                return Admin.getAdmin().login(params);
            default:
                return LOGIN_FAIL_WRONG_TYPE;
        }
    }

    public int register(HashMap<String, String> params){
        switch (params.remove("type")) {
            case "admin":
                return Admin.getAdmin().register(params);
            case "airline":
                Airline airline = new Airline();
                return airline.register(params);
            case "customer":
                Customer customer = new Customer();
                return customer.register(params);
            default:
                return REG_FAIL_NO_TYPE_SELECTED;
        }
    }
}
