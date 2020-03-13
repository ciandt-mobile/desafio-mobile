package com.ceandt.moviedb.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Movie {

    private float popularity;

    @SerializedName("vote_count")
    private float voteCount;

    private boolean video;

    @SerializedName("poster_path")
    private String posterPath;

    private int id;

    private boolean adult;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    private String title;

    @SerializedName("vote_average")
    private float voteAverage;

    private String overview;

    @SerializedName("release_date")
    private String releaseDate;


}
