package main.java.com.example.four_aces.domain;

public class Flight extends DomainObject {

    private String flightCode;

    private String flightDate;

    private String flightTime;

    public Flight(int flightId, String flightCode, String flightDate, String flightTime) {
        super(flightId);
        this.flightCode = flightCode;
        this.flightDate = flightDate;
        this.flightTime = flightTime;
        UnitOfWork.getInstance().registerNew(this);
    }

    public String getFlightCode() {
        return this.flightCode;
    }

    public String getFlightDate() {
        return this.flightDate;
    }

    public String getFlightTime() { return this.flightTime; }

}

