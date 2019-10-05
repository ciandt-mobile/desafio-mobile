package com.rbm.example.moviechallenge.data.api;

import com.rbm.example.moviechallenge.data.data.MovieDetailData;
import com.rbm.example.moviechallenge.data.data.MovieListData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("/3/movie/now_playing")
    Observable<MovieListData> getMovieList(@Query("api_key") String api_key, @Query("page") int page);

    @GET("/3/movie/{movie_id}")
    Observable<MovieDetailData> getMovieDetails(@Path("movie_id") int movie_id, @Query("api_key") String api_key);

}
