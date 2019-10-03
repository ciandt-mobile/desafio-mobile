package com.rbm.example.moviechallenge.data.repository.movies;

import com.rbm.example.moviechallenge.data.data.MovieListData;
import com.rbm.example.moviechallenge.data.repository.movies.remote.ApiMoviesDataSource;

import io.reactivex.Observable;

public class MovieRepositoryImpl {

    private static final String TAG = MovieRepositoryImpl.class.getSimpleName();

    public Observable<MovieListData> getMoviesList() {
        ApiMoviesDataSource apiSource = new ApiMoviesDataSource();
        return apiSource.getMovieList();
    }
}
