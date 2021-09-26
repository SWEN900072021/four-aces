package com.example.authentication;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CustomerCallbackHandler extends UserCallbackHandler {
    public CustomerCallbackHandler(HttpServletRequest request) {
        super(request);
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        super.handle(callbacks);
        NameCallback nameCallback = (NameCallback) callbacks[0];
        nameCallback.setName(request.getParameter("email"));

        // test only
        nameCallback.setName("test@email.com");
        ((PasswordCallback) callbacks[1]).setPassword("1234".toCharArray());
    }
}
