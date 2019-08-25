package com.svmdev.appmovies.data.webservice.results.success;

import com.google.gson.annotations.SerializedName;
import com.svmdev.appmovies.data.model.Movie;

import java.util.List;

public class MovieResult {

    @SerializedName("results")
    public List<Movie> results;

    @SerializedName("total_pages")
    public int totalPages;

}
