package com.pazyniuk.passive.model;

public class ItunesUser {
    private long id = 1L;
    private String username;
    private String password;
    private String joinedDate;
    private String name;
    private String surname;
    private int gender;
    private int creditCard;

    public final long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }

    public final String getUsername() {
        return username;
    }

    public final void setUsername(String username) {
        this.username = username;
    }

    public final String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        this.password = password;
    }

    public final String getJoinedDate() {
        return joinedDate;
    }

    public final void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final String getSurname() {
        return surname;
    }

    public final void setSurname(String surname) {
        this.surname = surname;
    }

    public final int getGender() {
        return gender;
    }

    public final void setGender(int gender) {
        this.gender = gender;
    }

    public final int getCreditCard() {
        return creditCard;
    }

    public final void setCreditCard(int creditCard) {
        this.creditCard = creditCard;
    }
}
