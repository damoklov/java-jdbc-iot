package com.pazyniuk.passive.model;

import java.io.Serializable;

public class Song implements Serializable {
    private long id = 1L;
    private int genre;
    private int author;
    private String name;
    private int recordLabel;
    private float price;
    private int downloadCount;
    private int album;

    public final int getGenre() {
        return genre;
    }

    public final void setGenre(int genre) {
        this.genre = genre;
    }

    public final int getAuthor() {
        return author;
    }

    public final void setAuthor(int author) {
        this.author = author;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final int getRecordLabel() {
        return recordLabel;
    }

    public final void setRecordLabel(int recordLabel) {
        this.recordLabel = recordLabel;
    }

    public final float getPrice() {
        return price;
    }

    public final void setPrice(float price) {
        this.price = price;
    }

    public final int getDownloadCount() {
        return downloadCount;
    }

    public final void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public final int getAlbum() {
        return album;
    }

    public final void setAlbum(int album) {
        this.album = album;
    }

    public final long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }
}
