package com.rangeldor.movieapp.api;

import com.rangeldor.movieapp.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("movie/popular?api_key=" + MovieClient.API_KEY + "&language=pt-BR&page=1")
    Call<Movie> getMovieToPopularity();

    @GET("movie/{movie_id}?api_key="+ MovieClient.API_KEY + "&language=pt-BR&page=1")
    Call<Movie.Result> getMovieById(@Path("movie_id") String movie_id);
}
