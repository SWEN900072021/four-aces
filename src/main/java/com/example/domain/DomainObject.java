package com.example.domain;

public abstract class DomainObject {
    protected Integer id;

    public DomainObject(Integer id) {
        this.id = id;
    }

    public DomainObject() {

    }

    public Integer getId() {
        return this.id;
    }

    @SuppressWarnings("unchecked")
    public <E> E cast(java.lang.Class<?> clazz){
        return (E) this;
    }
}
