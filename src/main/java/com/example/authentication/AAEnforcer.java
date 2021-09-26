package com.example.authentication;

import com.example.exception.TRSException;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

/**
 * AAEnforcer stands for Authentication and Authorisation enforcer
 */
public class AAEnforcer {

    private final LoginContext lc;
    private boolean loggedIn = false;
    private Subject subject;

    public AAEnforcer(HttpServletRequest request) throws LoginException, TRSException {
        URL jaasConfig = this.getClass().getClassLoader().getResource("TRSJaas.config");
        String configFile = jaasConfig != null ? jaasConfig.getFile() : null;
        System.setProperty("java.security.auth.login.config", configFile);
        switch (request.getParameter("type")){
            case "admin":
                lc = new LoginContext("admin", new AdminCallbackHandler(request));
                break;
            case "airline":
                lc = new LoginContext("airline", new AirlineCallbackHandler(request));
                break;
            case "customer":
                lc = new LoginContext("customer", new CustomerCallbackHandler(request));
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
