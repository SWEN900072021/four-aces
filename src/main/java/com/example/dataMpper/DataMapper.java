package com.example.dataMpper;

public interface DataMapper<E> {

    public E create(String...params);

    public void update(String...params);

    public void delete(String...params);

    public void find(String...params);
}
