package com.example.domain;

public class Passenger {
    private String passenger_id;
    private String passenger_firstName;
    private String passenger_lastName;
    private String identificationType;
    private String identificationNumber;

    public Passenger(String passenger_id, String passenger_firstName, String passenger_lastName,String identificationType, String identificationNumber){
        this.passenger_id = passenger_id;
        this.passenger_firstName = passenger_firstName;
        this.passenger_lastName = passenger_lastName;
        this.identificationType = identificationType;
        this.identificationNumber = identificationNumber;
    }


    public String getPassenger_id() {
        return passenger_id;
    }

    public String getPassenger_firstName() {
        return passenger_firstName;
    }

    public String getPassenger_lastName() {
        return passenger_lastName;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setPassenger_id(String passenger_id) {
        this.passenger_id = passenger_id;
    }

    public void setPassenger_firstName(String passenger_firstName) {
        this.passenger_firstName = passenger_firstName;
    }

    public void setPassenger_lastName(String passenger_lastName) {
        this.passenger_lastName = passenger_lastName;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
}
