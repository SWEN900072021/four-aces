package com.example.domain;

public class Airport extends DomainObject{
    private String referenceCode;
    private String address;

    public Airport(String referenceCode, String address) {
        super(null);
        this.referenceCode = referenceCode;
        this.address = address;
    }
    public String getReferenceCode() {
        return this.referenceCode;
    }

    public String getAddress() {
        return this.address;
    }

}
