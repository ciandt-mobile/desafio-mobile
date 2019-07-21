package com.msaviczki.themovieapp.presentation.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.msaviczki.themovieapp.R
import com.msaviczki.themovieapp.data.MovieMap
import com.msaviczki.themovieapp.helper.customview.Toggle
import com.msaviczki.themovieapp.helper.extension.*
import com.msaviczki.themovieapp.presentation.home.adapter.MovieListAdapter
import com.msaviczki.themovieapp.presentation.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()
    private val adapter = MovieListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bindObserver()
        bindViews()
        bindInitRequest()
    }

    private fun bindInitRequest() {
        if (viewModel.toggleState == Toggle.RoundSwitchState.ON_LEFT) viewModel.requestUpComingMovies()
        else viewModel.requestPopularMovies()
    }

    private fun bindViews() {
        toggle.select(viewModel.toggleState)
        toggle.setValueChangedAction {
            when (it) {
                Toggle.RoundSwitchState.ON_LEFT -> {
                    viewModel.requestUpComingMovies()
                }
                else -> {
                    viewModel.requestPopularMovies()
                }
            }
            viewModel.toggleState = it
        }
    }

    private fun bindObserver() {
        observe(viewModel.getMovieLiveData()) {
            showError(it.error)
            stateLoading(it.loading)
            successRequest(it.response)
        }
    }

    private fun successRequest(response: MutableList<MovieMap>) {
        if (response.isNullOrEmpty().not()) {
            adapter.addItems(response)
            recycler_movie.show()
            recycler_movie.adapter = adapter
            orientationState({
                recycler_movie.layoutManager = GridLayoutManager(this, 2)
            }, {
                recycler_movie.layoutManager = GridLayoutManager(this, 3)
            })
        }
    }

    private fun stateLoading(state: Boolean) {
        if (state) {
            recycler_movie.hide()
            showLoading()
        } else hideLoading()
    }

    private fun showError(error: String) {
        error.letIfNotEmpty {
            val snackbar = Snackbar.make(root_coordinator, error, Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }

    private fun showLoading() = loading.show()
    private fun hideLoading() = loading.hide()

}
