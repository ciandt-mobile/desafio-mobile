package com.rangeldor.movieapp.api;

import com.rangeldor.movieapp.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("discover/movie?api_key=" + MovieClient.API_KEY + "&primary_release_date.gte={release_date_gte}&language=pt-BR&sort_by=popularity.desc")
    Call<Movie> getMovieToPopularity(@Path ( "release_date_gte" ) String release_date_gte, @Query ( "page" ) String page);

    @GET("movie/{movie_id}?api_key="+ MovieClient.API_KEY + "&append_to_response=videos&language=pt-BR&page=1")
    Call<Movie.Result> getMovieById(@Path("movie_id") String movie_id);

    @GET("movie/{movie_id}/casts?api_key="+ MovieClient.API_KEY + "&append_to_response=videos&language=pt-BR&page=1")
    Call<Movie.Detail> getMovieCastsById(@Path("movie_id") String movie_id);
}
