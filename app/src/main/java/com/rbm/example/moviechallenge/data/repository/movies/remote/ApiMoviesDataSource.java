package com.rbm.example.moviechallenge.data.repository.movies.remote;

import android.util.Log;

import com.rbm.example.moviechallenge.data.api.Api;
import com.rbm.example.moviechallenge.data.api.ApiClient;
import com.rbm.example.moviechallenge.data.data.MovieData;
import com.rbm.example.moviechallenge.data.data.MovieListData;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiMoviesDataSource implements RemoteMoviesDataSource {

    public final static String TAG = ApiMoviesDataSource.class.getSimpleName();

    @Override
    public Observable<MovieListData> getMovieList() {
        Api api = ApiClient.getClient().create(Api.class);
        //TODO interceptor
        String api_key = "75c9554b8dfcd6303c5be0744fbf43e5";

        return api.getMovieList(api_key);
    }

    @Override
    public Observable<MovieData> getMovie(int movieId) {
        return null;
    }
}
