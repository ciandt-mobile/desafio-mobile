package com.ceandt.moviedb.service;

import com.ceandt.moviedb.model.DetailMovie;
import com.ceandt.moviedb.model.Movie;
import com.ceandt.moviedb.model.ResultMovie;
import com.ceandt.moviedb.repository.MovieDbRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MovieDbService {

    private final MovieDbRepository repository;

    @Inject
    public MovieDbService(final MovieDbRepository repository) {
        this.repository = repository;
    }

    public Observable<List<Movie>> getUpComingMovies() {
        return repository.getUpComing().map(ResultMovie::getMovies);
    }

    public Observable<List<Movie>> getPopularMovies() {
        return repository.getPopular().map(ResultMovie::getMovies);
    }

    public Observable<List<Movie>> getNowPlayingMovies() {
        return repository.getNowPlaying().map(ResultMovie::getMovies);
    }

    public Observable<List<Movie>> getTopRateMovies() {
        return repository.getTopRated().map(ResultMovie::getMovies);
    }

    public Observable<DetailMovie> getDetailMovie(final Integer movieId) {
        return repository.getDetail(movieId);
    }

}
