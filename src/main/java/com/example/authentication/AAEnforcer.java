package com.example.authentication;

import com.example.exception.TRSException;

import javax.security.auth.Subject;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

/**
 * AAEnforcer stands for Authentication and Authorisation enforcer
 */
public class AAEnforcer {

    private final LoginContext lc;
    private boolean loggedIn = false;
    private Subject subject;

    public AAEnforcer(HttpServletRequest request) throws LoginException, TRSException {
        Configuration config = new Configuration() {
            @Override
            public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
                AppConfigurationEntry[] entry = new AppConfigurationEntry[1];
                String Name = name.substring(0,1).toUpperCase() + name.substring(1);
                String loginModuleName = String.format("com.example.authentication.%sLoginModule", Name);
                HashMap<String, ?> options = new HashMap<>();
                entry[0] = new AppConfigurationEntry(loginModuleName, AppConfigurationEntry.LoginModuleControlFlag.REQUISITE, options);
                return entry;
            }
        };
        switch (request.getParameter("type")){
            case "admin":
                lc = new LoginContext("admin", null, new AdminCallbackHandler(request), config);
                break;
            case "airline":
                lc = new LoginContext("airline",null, new AirlineCallbackHandler(request), config);
                break;
            case "customer":
                lc = new LoginContext("customer",null, new CustomerCallbackHandler(request), config);
                break;
            default:
                throw new TRSException("Invalid User type");
        }
    }

    public void login(){
        try {
            lc.login();
            this.loggedIn = true;
            this.subject = lc.getSubject();
        } catch (LoginException e) {
            e.printStackTrace();
            this.loggedIn = false;
        }
    }

    public Subject getSubject(){
        return this.subject;
    }

    public boolean isLoggedIn(){
        return this.loggedIn;
    }

}
