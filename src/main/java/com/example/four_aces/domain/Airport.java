package com.example.four_aces.domain;

public class Airport {
    private String referenceCode;
    private String address;

    public Airport(String referenceCode, String address) {
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
