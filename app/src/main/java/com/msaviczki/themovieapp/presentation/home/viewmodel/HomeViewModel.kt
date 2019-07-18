package com.msaviczki.themovieapp.presentation.home.viewmodel

import androidx.lifecycle.ViewModel

interface HomeViewModelImpl {
    fun requestMovies()
}

class HomeViewModel : ViewModel(), HomeViewModelImpl {



    override fun requestMovies() {

    }

}