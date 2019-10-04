package com.rbm.example.moviechallenge.app.feature.list;

import android.util.Log;

import com.rbm.example.moviechallenge.app.feature.BaseViewModel;
import com.rbm.example.moviechallenge.data.repository.movies.MovieRepositoryImpl;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieListViewModel extends BaseViewModel<MovieListViewState> {

    private static final String TAG = MovieListViewModel.class.getSimpleName();
    private MovieRepositoryImpl movieRepository = new MovieRepositoryImpl();

    public MovieListViewModel() {
        this.viewState = new MovieListViewState();
    }

    public void loadMovies() {
        loadPage(1);
    }

    public void loadNextPage() {
        int page = Objects.requireNonNull(viewState.getMovieList().getValue()).getPage();
        int totalPages = viewState.getMovieList().getValue().getTotal_pages();
        boolean isLoading = viewState.getIsLoading().getValue();

        if (page <= totalPages && !isLoading) {
            page++;
            loadPage(page);

            Log.d(TAG, "Loading next page " + page);
        }
    }

    private void loadPage(int page) {
        Disposable disposable = movieRepository.getMoviesList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(data -> {
                    viewState.getIsLoading().setValue(true);
                    Log.d(TAG, "View Model is loading");
                })
                //.doOnEvent((movieData, throwable) -> Log.d(TAG, "View Model is not loading anymore"))
                .subscribe(
                        data -> {
                            viewState.getMovieList().setValue(data);
                            viewState.getIsLoading().setValue(false);
                            Log.d(TAG, "Data Received -> " + data.getPage() + " " + data.getTotal_results());
                        },
                        error -> Log.d(TAG, "Data error")
                );

        disposableContainer.add(disposable);
    }
}
