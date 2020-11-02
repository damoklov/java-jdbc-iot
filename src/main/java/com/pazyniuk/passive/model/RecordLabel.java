package com.pazyniuk.passive.model;

public class RecordLabel {
    private long id = 1L;
    private String title;
    private String yearEstablished;

    public final long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }

    public final String getTitle() {
        return title;
    }

    public final void setTitle(String title) {
        this.title = title;
    }

    public final String getYearEstablished() {
        return yearEstablished;
    }

    public final void setYearEstablished(String yearEstablished) {
        this.yearEstablished = yearEstablished;
    }
}
