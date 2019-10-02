package com.rbm.example.moviechallenge.data.api;

import com.rbm.example.moviechallenge.data.data.MovieListData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "https://api.themoviedb.org/";

    @GET("/3/discover/movie")
    Call<MovieListData> getMovieList(@Query("api_key") String api_key);
}
