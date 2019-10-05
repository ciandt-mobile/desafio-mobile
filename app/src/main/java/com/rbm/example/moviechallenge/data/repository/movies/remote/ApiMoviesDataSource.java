package com.rbm.example.moviechallenge.data.repository.movies.remote;

import com.rbm.example.moviechallenge.data.api.Api;
import com.rbm.example.moviechallenge.data.api.ApiClient;
import com.rbm.example.moviechallenge.data.data.MovieData;
import com.rbm.example.moviechallenge.data.data.MovieListData;

import io.reactivex.Observable;

public class ApiMoviesDataSource implements RemoteMoviesDataSource {

    public final static String TAG = ApiMoviesDataSource.class.getSimpleName();

    @Override
    public Observable<MovieListData> getMovieList(int page) {
        Api api = ApiClient.getClient().create(Api.class);

        return api.getMovieList(ApiClient.API_KEY, page);
    }

    @Override
    public Observable<MovieData> getMovie(int movieId) {
        return null;
    }
}
