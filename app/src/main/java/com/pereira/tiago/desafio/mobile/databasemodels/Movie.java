package com.pereira.tiago.desafio.mobile.databasemodels;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Movie implements Serializable {

    private static final long serialVersionUID = 8190126856092181639L;

    int id;
    String title;
    String release_date;
    String backdrop_path;
    String poster_path;
    @Generated(hash = 893437426)
    public Movie(int id, String title, String release_date, String backdrop_path,
            String poster_path) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.backdrop_path = backdrop_path;
        this.poster_path = poster_path;
    }
    @Generated(hash = 1263461133)
    public Movie() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getRelease_date() {
        return this.release_date;
    }
    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
    public String getBackdrop_path() {
        return this.backdrop_path;
    }
    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
    public String getPoster_path() {
        return this.poster_path;
    }
    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
