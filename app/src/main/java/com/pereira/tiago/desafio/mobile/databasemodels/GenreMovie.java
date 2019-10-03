package com.pereira.tiago.desafio.mobile.databasemodels;

import org.greenrobot.greendao.annotation.Entity;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GenreMovie implements Serializable {

    private final static long serialVersionUID = 2162048571212201244L;
    
    int movie_id;
    int genre_id;
    String name;
    @Generated(hash = 1035191358)
    public GenreMovie(int movie_id, int genre_id, String name) {
        this.movie_id = movie_id;
        this.genre_id = genre_id;
        this.name = name;
    }
    @Generated(hash = 1220764716)
    public GenreMovie() {
    }
    public int getMovie_id() {
        return this.movie_id;
    }
    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }
    public int getGenre_id() {
        return this.genre_id;
    }
    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
