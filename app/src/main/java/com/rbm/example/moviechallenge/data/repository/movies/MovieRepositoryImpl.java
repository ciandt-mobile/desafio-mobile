package com.rbm.example.moviechallenge.data.repository.movies;

import com.rbm.example.moviechallenge.data.data.MovieDetailData;
import com.rbm.example.moviechallenge.data.data.MovieListData;
import com.rbm.example.moviechallenge.data.repository.movies.remote.ApiMoviesDataSource;

import io.reactivex.Observable;

public class MovieRepositoryImpl {

    private static final String TAG = MovieRepositoryImpl.class.getSimpleName();
    private ApiMoviesDataSource apiSource;

    public MovieRepositoryImpl() {
        this.apiSource = new ApiMoviesDataSource();
    }

    public Observable<MovieListData> getMoviesList(int page) {
        return apiSource.getMovieList(page);
    }

    public Observable<MovieDetailData> getMovie(int movieId) {
        return apiSource.getMovie(movieId);
    }
}
