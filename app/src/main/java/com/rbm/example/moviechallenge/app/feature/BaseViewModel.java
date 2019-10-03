package com.rbm.example.moviechallenge.app.feature;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel<STATE> extends ViewModel {

    public MutableLiveData<STATE> viewState = new MutableLiveData<>();
    protected CompositeDisposable disposableContainer = new CompositeDisposable();

    public void onCleared() {
        super.onCleared();
        disposableContainer.clear();
    }

    public STATE currentViewState() {
        return viewState.getValue();
    }
}
