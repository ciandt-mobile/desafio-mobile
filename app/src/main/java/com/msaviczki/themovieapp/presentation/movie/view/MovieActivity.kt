package com.msaviczki.themovieapp.presentation.movie.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.msaviczki.themovieapp.R
import com.msaviczki.themovieapp.data.MovieMap
import com.msaviczki.themovieapp.helper.customview.Toggle
import com.msaviczki.themovieapp.helper.extension.*
import com.msaviczki.themovieapp.presentation.detail.view.MovieDetailActivity
import com.msaviczki.themovieapp.presentation.movie.adapter.MovieListAdapter
import com.msaviczki.themovieapp.presentation.movie.adapter.MovieListViewHolder
import com.msaviczki.themovieapp.presentation.movie.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_movie.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieActivity : AppCompatActivity(), MovieListViewHolder.MovieSelectListener {

    private val viewModel: MovieViewModel by viewModel()
    private val adapter = MovieListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
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

    override fun onMovieSelect(id: Int) {
        start<MovieDetailActivity>()
    }

    private fun showLoading() = loading.show()
    private fun hideLoading() = loading.hide()

}
