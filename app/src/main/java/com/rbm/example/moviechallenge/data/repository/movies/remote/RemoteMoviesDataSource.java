package com.rbm.example.moviechallenge.data.repository.movies.remote;

import com.rbm.example.moviechallenge.data.data.MovieData;

import java.util.List;

interface RemoteMoviesDataSource {

    List<MovieData> getMovieList();

    MovieData getMovie(int movieId);
}
