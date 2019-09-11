package com.rangeldor.movieapp.api;

import com.rangeldor.movieapp.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodApi {

    @GET("movie/popular?api_key=" + FoodClient.API_KEY + "&language=pt-BR&page=1")
    Call<Movie> getMovieToPopularity();
}
