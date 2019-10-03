package com.rbm.example.moviechallenge.app.feature.list;

import android.util.Log;

import com.rbm.example.moviechallenge.app.feature.BaseViewModel;
import com.rbm.example.moviechallenge.data.repository.movies.MovieRepositoryImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieListViewModel extends BaseViewModel<MovieListViewState> {

    private static final String TAG = MovieListViewModel.class.getSimpleName();

    public void loadMovies() {
        MovieRepositoryImpl movieRepository = new MovieRepositoryImpl();

        Disposable disposable = movieRepository.getMoviesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(data -> {
                    //viewState.postValue(new MovieListViewState().setLoading(true));
                    Log.d(TAG, "View Model is loading");
                })
                .doOnEvent((movieData, throwable) -> Log.d(TAG, "View Model is not loading anymore"))
                .subscribe(
                        data -> Log.d(TAG, "Data Received"),
                        error -> Log.d(TAG, "Data error")
                );

        disposableContainer.add(disposable);
    }
}
