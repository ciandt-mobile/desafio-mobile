package com.msaviczki.themovieapp.presentation.detail.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.msaviczki.themovieapp.R
import com.msaviczki.themovieapp.data.MovieDetailMap
import com.msaviczki.themovieapp.helper.extension.*
import com.msaviczki.themovieapp.presentation.detail.viewmodel.MovieDetailViewModel
import com.msaviczki.themovieapp.presentation.movie.view.MovieActivity
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private val viewModel: MovieDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        bindObserver()
        bindInitRequest()
    }

    private fun bindInitRequest() {
        val id = intent.getIntExtra(MovieActivity.ID, -1)

        viewModel.getMovieDetail(id)
    }

    private fun bindObserver() {
        observe(viewModel.getMovieLiveData()) {
            stateLoading(it.loading)
            showError(it.error)
            bindResponse(it.response)
        }
    }

    private fun bindResponse(response: MovieDetailMap?) {
        response?.let {
            img_poster.loadUrlImage(it.image)
            img_back.loadUrlImage(it.backdropPath)
            txt_title.text = it.title
            txt_genre.text = it.genre
            txt_rate.text = it.rating
            txt_duration.text = it.time
            txt_description.text = it.overiew
            txt_release.text = it.realeseDate
            txt_language.text = it.originalLanguage
            txt_original_title.text = it.originalTitle
            scroll.show()
        }
    }

    private fun stateLoading(state: Boolean) {
        if (state) {
            scroll.hide()
            showLoading()
        } else hideLoading()
    }

    private fun showError(error: String) {
        error.letIfNotEmpty {
            val snackbar = Snackbar.make(root, error, Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }

    private fun showLoading() = loading.show()
    private fun hideLoading() = loading.hide()
}