package com.pazyniuk.passive.model;

public class UserSong {
    private long id = 1L;
    private long idUser = 1L;
    private long idSong = 1L;

    public final long getIdUser() {
        return idUser;
    }

    public final void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public final long getIdSong() {
        return idSong;
    }

    public final void setIdSong(long idSong) {
        this.idSong = idSong;
    }

    public final long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }
}
