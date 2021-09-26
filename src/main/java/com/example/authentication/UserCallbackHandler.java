package com.example.authentication;

import com.example.domain.User;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public abstract class UserCallbackHandler implements CallbackHandler {

    protected HttpServletRequest request;

    public UserCallbackHandler(HttpServletRequest request){
        super();
        this.request = request;
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        PasswordCallback passwordCallback = (PasswordCallback) callbacks[1];
        passwordCallback.setPassword(request.getParameter("password").toCharArray());
    }

    public void loginSuccess(User user){
        this.request.getSession().setAttribute("user",user);
    }
}
