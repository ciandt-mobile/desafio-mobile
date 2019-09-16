package com.rangeldor.movieapp.api;

import com.rangeldor.movieapp.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("discover/movie?api_key=" + MovieClient.API_KEY + "&sort_by=popularity.desc")
    Call<Movie> getMovieToPopularity(@Query ( "primary_release_date.gte" ) String release_date_gte,
                                     @Query ( "page" ) String page,
                                     @Query ( "language") String language );

    @GET("movie/{movie_id}?api_key="+ MovieClient.API_KEY + "&append_to_response=videos")
    Call<Movie.Result> getMovieById(@Path("movie_id") String movie_id,
                                    @Query ( "page" ) String page,
                                    @Query ( "language") String language );

    @GET("movie/{movie_id}/casts?api_key="+ MovieClient.API_KEY + "&append_to_response=videos")
    Call<Movie.Detail> getMovieCastsById(@Path("movie_id") String movie_id,
                                         @Query ( "page" ) String page,
                                         @Query ( "language") String language );
}
