package com.rbm.example.moviechallenge.app.feature.detail;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.rbm.example.moviechallenge.app.feature.BaseViewModel;
import com.rbm.example.moviechallenge.app.feature.list.MovieListViewModel;
import com.rbm.example.moviechallenge.data.data.MovieDetailData;
import com.rbm.example.moviechallenge.data.repository.movies.MovieRepositoryImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailViewModel extends BaseViewModel<MutableLiveData<MovieDetailData>> {

    private static final String TAG = MovieListViewModel.class.getSimpleName();
    private MovieRepositoryImpl movieRepository = new MovieRepositoryImpl();

    public MovieDetailViewModel() {
        this.viewState = new MutableLiveData<MovieDetailData>();
    }

    public void loadMovieDetail(int movieId) {
        Disposable disposable = movieRepository.getMovie(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        data -> {
                            viewState.postValue(data);
                            Log.d(TAG, "Data Received -> " + data.getTitle() + " " + data.getId());
                        },
                        error -> Log.d(TAG, "Data error")
                );

        disposableContainer.add(disposable);
    }
}
