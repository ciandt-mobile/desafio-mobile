package com.rangeldor.movieapp.api;

import com.rangeldor.movieapp.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {

    @GET("movie/popular?api_key=" + MovieClient.API_KEY + "&language=pt-BR&page=1")
    Call<Movie> getMovieToPopularity();
}
