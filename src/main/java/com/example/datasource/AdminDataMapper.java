package com.example.datasource;

import com.example.domain.Admin;
import com.example.exception.NoRecordFoundException;
import com.example.exception.TRSException;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    public void insert(Admin obj) throws SQLException, TRSException, NoRecordFoundException {
        try {
            findById(1);
            throw new TRSException("Admin account has been created");
        }catch ( NoRecordFoundException e ){
            super.insert(obj);
        }
    }

    @Override
    public Admin newDomainObject(ResultSet rs) throws SQLException{
        String username = rs.getString(prefix+"username");
        String password = rs.getString(prefix+"password");
        int id = rs.getInt(prefix+"id");
        return new Admin(id, username, password);
    }

    @Override
    public Admin createUser(int id, String username, String email, String password) {
        return new Admin(id, username, password);
    }
}
