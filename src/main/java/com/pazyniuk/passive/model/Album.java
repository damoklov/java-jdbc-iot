package com.pazyniuk.passive.model;

public class Album {
    private long id = 1L;
    private String name;
    private String year;

    public final long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final String getYear() {
        return year;
    }

    public final void setYear(String year) {
        this.year = year;
    }
}
