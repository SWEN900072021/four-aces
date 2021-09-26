package com.example.authentication;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AdminCallbackHandler extends UserCallbackHandler {

    public AdminCallbackHandler(HttpServletRequest request) {
        super(request);
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        super.handle(callbacks);
        NameCallback nameCallback = (NameCallback) callbacks[0];
        nameCallback.setName(request.getParameter("username"));

        // test only
        nameCallback.setName("sean");
        ((PasswordCallback) callbacks[1]).setPassword("1234567".toCharArray());
    }
}
