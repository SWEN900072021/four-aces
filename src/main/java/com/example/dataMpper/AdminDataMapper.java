package com.example.dataMpper;

import com.example.trs.Admin;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminDataMapper extends UserDataMapper<Admin> {

    public AdminDataMapper() {
        super("administrator", "administrator_", Admin.getAdmin());

    }

    @Override
    public Admin create(HashMap<String, String> params) {
        return (Admin) super.create(params);
    }



}
