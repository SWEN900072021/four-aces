package main.java.com.example.four_aces.domain;

public class Flight extends DomainObject {
    private String code;
    private String date;
    private String time;

    public Flight(int id, String code, String date, String time) {
        super(id);
        this.code = code;
        this.date = date;
        this.time = time;
        UnitOfWork.getInstance().registerNew(this);
    }

    public String getFlightCode() {
        return this.code;
    }

    public String getFlightDate() {
        return this.date;
    }

    public String getFlightTime() {
        return this.time;
    }

}

