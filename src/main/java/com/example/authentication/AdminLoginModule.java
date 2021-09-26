package com.example.authentication;


import com.example.datasource.AdminDataMapper;
import com.example.domain.User;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminLoginModule extends UserLoginModule {

    public AdminLoginModule(){
        this.callbacks = new Callback[2];
        callbacks[0] = new NameCallback("Username :");
        callbacks[1] = new PasswordCallback("Password :", false);
    }

    @Override
    public ArrayList<User> getUsers() throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", ((NameCallback) callbacks[0]).getName());
        return new ArrayList<>(AdminDataMapper.getInstance().find(params));
    }

    @Override
    public void setPrincipal(String name) {
        this.principal = new AdminPrincipal();
    }
}
