package com.ceandt.moviedb.repository;


import com.ceandt.moviedb.model.DetailMovie;
import com.ceandt.moviedb.model.ResultMovie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieDbRepository {

    @GET("movie/upcoming")
    Observable<ResultMovie> getUpComing();

    @GET("movie/popular")
    Observable<ResultMovie> getPopular();

    @GET("movie/now_playing")
    Observable<ResultMovie> getNowPlaying();

    @GET("movie/top_rated")
    Observable<ResultMovie> getTopRated();

    @GET("movie/{movie_id}?append_to_response=credits")
    Observable<DetailMovie> getDetail(@Path("movie_id") Integer movieId);

}
