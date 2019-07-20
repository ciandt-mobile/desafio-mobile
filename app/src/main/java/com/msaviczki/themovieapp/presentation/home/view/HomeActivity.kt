package com.msaviczki.themovieapp.presentation.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.msaviczki.themovieapp.R
import com.msaviczki.themovieapp.data.MovieListResponse
import com.msaviczki.themovieapp.helper.extension.*
import com.msaviczki.themovieapp.presentation.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bindObserver()
        viewModel.requestMovies()
    }

    private fun bindObserver() {
        observe(viewModel.getMovieLiveData()){
            showError(it.error)
            stateLoading(it.loading)
        }
    }

    private fun stateLoading(state: Boolean) {
        if (state) showLoading() else hideLoading()
    }

    private fun successRequest(response: MovieListResponse?) {

    }

    private fun showError(error: String) {
        error.letIfNotEmpty {
            val snackbar = Snackbar.make(root_coordinator, error, Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }

    private fun hideLoading() = loading.hide()

    private fun showLoading() = loading.show()

}
