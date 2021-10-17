package com.example.domain;

import java.util.Objects;

public class Airport extends DomainObject{
    private String referenceCode;
    private String address;

    public Airport(Integer id, String referenceCode, String address) {
        super(id);
        this.referenceCode = referenceCode;
        this.address = address;
        UnitOfWork.getCurrent().registerNew(this);
    }
    public String getReferenceCode() {
        return this.referenceCode;
    }

    public String getAddress() {
        return this.address;
    }

}
