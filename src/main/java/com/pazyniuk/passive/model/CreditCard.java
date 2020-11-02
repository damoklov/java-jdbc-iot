package com.pazyniuk.passive.model;

public class CreditCard {
    private long id = 1L;
    private String number;

    public final long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }

    public final String getNumber() {
        return number;
    }

    public final void setNumber(String number) {
        this.number = number;
    }
}
