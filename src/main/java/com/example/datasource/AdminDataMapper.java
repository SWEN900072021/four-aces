package com.example.datasource;

import com.example.domain.Admin;
import com.example.domain.User;
import com.example.exception.TRSException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminDataMapper extends UserDataMapper<Admin> {

    private static AdminDataMapper _instance;

    private AdminDataMapper() {
        super("administrator", "administrator_");
        this.fields = new String[]{this.prefix + "username", this.prefix + "password"};
        this.pkey = this.prefix + "id";
    }

    public static AdminDataMapper getInstance(){
        if( _instance == null ){
            return _instance = new AdminDataMapper();
        }
        return _instance;
    }

    @Override
    public void insert(Admin obj) throws Exception {
        System.out.println(obj);
        try {
            findById(1);
        }catch ( TRSException e ){
            if( e.getMessage().equals("No record with this id was found in the database") ){
                super.insert(obj);
            }
            else{
                update(obj);
            }
        }
    }

    @Override
    public Admin newDomainObject(ResultSet rs) throws Exception{
        return Admin.getAdmin();
    }
}
