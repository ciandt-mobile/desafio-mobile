package com.pereira.tiago.desafio.mobile.databasemodels;

import java.io.Serializable;
import java.util.List;

public class SendDetails implements Serializable {

    private final static long serialVersionUID = 3862641797135478821L;

    Details details;
    List<GenreMovie> genreMovieList;
    List<CastMovie> castMovieList;

    public SendDetails(){}

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public List<GenreMovie> getGenreMovieList() {
        return genreMovieList;
    }

    public void setGenreMovieList(List<GenreMovie> genreMovieList) {
        this.genreMovieList = genreMovieList;
    }

    public List<CastMovie> getCastMovieList() {
        return castMovieList;
    }

    public void setCastMovieList(List<CastMovie> castMovieList) {
        this.castMovieList = castMovieList;
    }
}
