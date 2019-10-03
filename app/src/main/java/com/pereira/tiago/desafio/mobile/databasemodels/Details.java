package com.pereira.tiago.desafio.mobile.databasemodels;

import org.greenrobot.greendao.annotation.Entity;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Details implements Serializable {

    private final static long serialVersionUID = 5673151277522239484L;
    
    int id;
    boolean adult;
    String title;
    String backdrop_path;
    String release_date;
    String overview;
    String poster_path;
    int runtime;

    @Generated(hash = 1098240420)
    public Details(int id, boolean adult, String title, String backdrop_path,
            String release_date, String overview, String poster_path, int runtime) {
        this.id = id;
        this.adult = adult;
        this.title = title;
        this.backdrop_path = backdrop_path;
        this.release_date = release_date;
        this.overview = overview;
        this.poster_path = poster_path;
        this.runtime = runtime;
    }
    @Generated(hash = 924803291)
    public Details() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean getAdult() {
        return this.adult;
    }
    public void setAdult(boolean adult) {
        this.adult = adult;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBackdrop_path() {
        return this.backdrop_path;
    }
    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
    public String getRelease_date() {
        return this.release_date;
    }
    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
    public String getOverview() {
        return this.overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getPoster_path() {
        return this.poster_path;
    }
    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
    public int getRuntime() {
        return this.runtime;
    }
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
}
