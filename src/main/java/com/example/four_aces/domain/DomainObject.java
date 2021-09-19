package main.java.com.example.four_aces.domain;

public abstract class DomainObject {
    private int id;

    public DomainObject(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
