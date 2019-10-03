package com.rbm.example.moviechallenge.data.repository.movies.remote;

import com.rbm.example.moviechallenge.data.data.MovieData;
import com.rbm.example.moviechallenge.data.data.MovieListData;

import io.reactivex.Observable;

interface RemoteMoviesDataSource {

    Observable<MovieListData> getMovieList();

    Observable<MovieData> getMovie(int movieId);
}
