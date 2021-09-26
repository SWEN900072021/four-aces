package com.example.authentication;

import com.example.domain.User;
import com.example.exception.TRSException;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.util.ArrayList;
import java.util.Map;

public abstract class UserLoginModule implements LoginModule {

    protected Subject subject;
    protected CallbackHandler callbackHandler;
    protected Callback[] callbacks;
    protected UserPrincipal principal;
    protected Exception e;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.callbackHandler = callbackHandler;
        this.subject = subject;
    }

    @Override
    public boolean login() throws LoginException {
        try {
            callbackHandler.handle(callbacks);
            String password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());
            String name = ((NameCallback) callbacks[0]).getName();
            ArrayList<User> users = getUsers();
            if (users.size() == 0) {
                throw new TRSException("Account cannot be found");
            }
            if (users.size() != 1) {
                throw new TRSException("Multiple found in the ");
            }
            User user = users.get(0);
            user.login(password);
            setPrincipal(name);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            this.e = e;
            return false;
        }
    }

    @Override
    public boolean commit() throws LoginException {
        if( subject !=null && !subject.getPrincipals().contains(this.principal) ){
            this.subject.getPrincipals().add(this.principal);
            return true;
        }
        return false;
    }

    @Override
    public boolean abort() throws LoginException {
        if (this.subject != null && this.principal != null){
            this.subject.getPrincipals().remove(this.principal);
        }
        this.subject = null;
        this.principal = null;
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        this.subject.getPrincipals().remove(this.principal);
        this.subject = null;
        return true;
    }

    public abstract ArrayList<User> getUsers() throws Exception;

    public abstract void setPrincipal(String name);
}
