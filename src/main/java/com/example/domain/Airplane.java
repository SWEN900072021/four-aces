package com.example.domain;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Airplane extends DomainObject {

    private String type;
    private String seatsString;

    public Airplane(Integer id, String type, String seatsString) {
        super(id);
        this.type = type;
        this.seatsString = seatsString;
    }

    public String getType() {
        return this.type;
    }

    public String getSeatsString() {
        return this.seatsString;
    }

    public List<Seat> getSeats() throws Exception {
        List<Seat> seats = new ArrayList<>();
        JSONObject seatsObj = new JSONObject(this.seatsString);
        JSONArray seatsArr = seatsObj.getJSONArray("seats");
        for (int i = 0; i < seatsArr.length(); i++) {
            String seatNumber = seatsArr.getJSONObject(i).getString("seat_number");
            String seatClass = seatsArr.getJSONObject(i).getString("seat_class");
            Seat seat = new Seat(seatNumber, seatClass);
            seats.add(seat);
        }
        return seats;
    }
}
