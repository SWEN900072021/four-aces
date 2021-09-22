package com.example.domain;

public class Flight extends DomainObject {
    private String code;
    private String date;
    private String time;
    private String source;
    private String destination;
    private String stopovers;
    private Integer airline_id;
    private Integer airplane_id;

    public Flight(Integer id, String code, String date, String time) {
        super(id);
        this.code = code;
        this.date = date;
        this.time = time;
        UnitOfWork.getInstance().registerNew(this);
    }

    public String getCode() {
        return this.code;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public void setCode(String code) {
        this.code = code;
        UnitOfWork.getInstance().registerDirty(this);
    }

    public void setDate(String date) {
        this.date = date;
        UnitOfWork.getInstance().registerDirty(this);
    }

    public void setTime(String time) {
        this.time = time;
        UnitOfWork.getInstance().registerDirty(this);
    }
}

