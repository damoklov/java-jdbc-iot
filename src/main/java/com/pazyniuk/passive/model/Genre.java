package com.pazyniuk.passive.model;

public class Genre {
    private long id = 1L;
    private String name;

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
}
