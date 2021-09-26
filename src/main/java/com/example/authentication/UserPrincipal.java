package com.example.authentication;

import java.security.Principal;

public abstract class UserPrincipal implements Principal {

    protected final String name;

    public UserPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
