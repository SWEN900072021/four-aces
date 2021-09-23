package com.example.domain;

public class Airport extends DomainObject{
    private String referenceCode;
    private String address;

    public Airport(Integer id, String referenceCode, String address) {
        super(id);
        this.referenceCode = referenceCode;
        this.address = address;
        UnitOfWork.getInstance().registerNew(this);
    }
    public String getReferenceCode() {
        return this.referenceCode;
    }

    public String getAddress() {
        return this.address;
    }

    public void setReferenceCode(String referenceCode){
        this.referenceCode = referenceCode;
        UnitOfWork.getInstance().registerDirty(this);
    }

    public void setAddress(String address){
        this.address = address;
        UnitOfWork.getInstance().registerDirty(this);
    }
}
