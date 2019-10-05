package com.rbm.example.moviechallenge.data.repository.movies.remote;

import com.rbm.example.moviechallenge.data.api.Api;
import com.rbm.example.moviechallenge.data.api.ApiClient;
import com.rbm.example.moviechallenge.data.data.MovieData;
import com.rbm.example.moviechallenge.data.data.MovieDetailData;
import com.rbm.example.moviechallenge.data.data.MovieListData;

import io.reactivex.Observable;

public class ApiMoviesDataSource {

    public final static String TAG = ApiMoviesDataSource.class.getSimpleName();

    public ApiMoviesDataSource() {
        this.api = ApiClient.getClient().create(Api.class);
    }

    private Api api;

    public Observable<MovieListData> getMovieList(int page) {
        return api.getMovieList(ApiClient.API_KEY, page);
    }

    public Observable<MovieDetailData> getMovie(int movieId) {
        return api.getMovieDetails(movieId, ApiClient.API_KEY);
    }
}
