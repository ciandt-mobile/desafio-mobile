package com.ciet.leogg.filmes.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Date;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Movie {
    private Long id;
    private String posterPath;
    private String backdropPath;
    private Date releaseDate;
    private String title;
    private Integer[] genreIds;
    private String overview;

    public Long getId() {
        return id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public Integer[] getGenreIds() {
        return genreIds;
    }

    public String getOverview() {
        return overview;
    }
}
