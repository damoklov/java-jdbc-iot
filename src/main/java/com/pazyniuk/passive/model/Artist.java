package com.pazyniuk.passive.model;

public class Artist {
    private long id = 1L;
    private String name;
    private int country;
    private int gender;

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

    public final int getCountry() {
        return country;
    }

    public final void setCountry(int country) {
        this.country = country;
    }

    public final int getGender() {
        return gender;
    }

    public final void setGender(int gender) {
        this.gender = gender;
    }
}
