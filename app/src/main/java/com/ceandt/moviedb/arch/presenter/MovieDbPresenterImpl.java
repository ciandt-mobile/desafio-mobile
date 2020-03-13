package com.ceandt.moviedb.arch.presenter;

import com.ceandt.moviedb.arch.view.DetailMovieView;
import com.ceandt.moviedb.arch.view.MoviesView;
import com.ceandt.moviedb.model.Movie;
import com.ceandt.moviedb.preferences.Preferences;
import com.ceandt.moviedb.service.MovieDbService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDbPresenterImpl implements MovieDbPresenter {

    private final MovieDbService service;
    private MoviesView moviesView;
    private DetailMovieView detailMovieView;
    private List<Disposable> disposables = new ArrayList<>();

    @Inject
    public MovieDbPresenterImpl(final MovieDbService service) {
        this.service = service;
    }

    @Override
    public void loadMovies(final String type) {
        callServiceByPreference(type);
        callService(service.getNowPlayingMovies(), movies -> moviesView.onLoadNowPlayingMovies(movies));
        callService(service.getTopRateMovies(), movies -> moviesView.onLoadTopRatedMovies(movies));
    }

    @Override
    public void switchPreference(String type) {
        callServiceByPreference(type);
    }

    @Override
    public void setView(final MoviesView view) {
        this.moviesView = view;
    }

    @Override
    public void setView(DetailMovieView view) {
        this.detailMovieView = view;
    }

    @Override
    public void loadMovieDetail(Integer movieId) {
        disposables.add(service.getDetailMovie(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(detailMovieView::loadDetailMovie));
    }

    @Override
    public void dispose() {
        disposables.forEach(it -> {
            if (!it.isDisposed()) {
                it.dispose();
            }
        });
        disposables.clear();
    }

    private void callServiceByPreference(final String type) {
        Observable<List<Movie>> observableMovies;
        if (type.equals(Preferences.POPULAR_VALUE)) {
            observableMovies = service.getPopularMovies();
        } else {
            observableMovies = service.getUpComingMovies();
        }
        callService(observableMovies, movies -> moviesView.onLoadUpComingOrPopularMovies(movies));
    }

    private void callService(final Observable<List<Movie>> observable, final ViewCallback callback) {
        disposables.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::call));
    }

    @FunctionalInterface
    interface ViewCallback {
        void call(List<Movie> movies);
    }

}
