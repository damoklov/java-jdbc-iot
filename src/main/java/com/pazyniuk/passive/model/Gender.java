package com.pazyniuk.passive.model;

public class Gender {
    private long id = 1L;
    private String genderType;

    public final long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }

    public final String getGenderType() {
        return genderType;
    }

    public final void setGenderType(String genderType) {
        this.genderType = genderType;
    }
}
