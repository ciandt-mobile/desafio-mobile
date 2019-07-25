package com.thiagoseiji.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T> : ViewModel() {

    abstract fun getData(): LiveData<T>
    abstract fun saveToDatabase(data: T)
}