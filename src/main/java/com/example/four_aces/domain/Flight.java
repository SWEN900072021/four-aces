package main.java.com.example.four_aces.domain;

public class Flight {
    private int id;

    private String code;

    private String date;

    private String time;

    public Flight(int id, String code, String date, String time) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() { return this.time; }

}

