package com.rbm.example.moviechallenge.app.feature;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel<STATE> extends ViewModel {

    public STATE viewState;
    protected CompositeDisposable disposableContainer = new CompositeDisposable();

    public void onCleared() {
        super.onCleared();
        disposableContainer.clear();
    }
}
