package main.java.com.example.four_aces.domain;

public abstract class DomainObject {
    private Integer id;

    public DomainObject(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }
}
