package com.example.domain;

import java.util.Objects;

public class Passenger extends DomainObject {
    private String firstName;
    private String lastName;
    private String idType;
    private String idNumber;

    public Passenger(Integer id, String firstName, String lastName,String idType, String idNumber){
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.idType = idType;
        this.idNumber = idNumber;
        UnitOfWork.getCurrent().registerNew(this);
    }


    public Integer getId() {
        return this.id;
    }

    public String getfirstName() {
        return firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public String getIdentificationType() {
        return idType;
    }

    public String getIdentificationNumber() {
        return idNumber;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
