package com.apolo.findmovies.presentation.movieDetail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apolo.findmovies.R
import com.apolo.findmovies.data.model.MovieDetailViewModel
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_MOVIE_DETAILS = "extra_movie_details"

        fun getStartIntent(context: Context, movieDetail : MovieDetailViewModel) = Intent(context, MovieDetailActivity::class.java).apply {
            this.putExtra(EXTRA_MOVIE_DETAILS, movieDetail)
        }
    }

    private val movieDetails : MovieDetailViewModel by lazy {
        intent.extras.get(EXTRA_MOVIE_DETAILS) as MovieDetailViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movie_name.text = movieDetails.name
        movie_duration.text = movieDetails.duration
        movie_category.text = movieDetails.category
        movie_description.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. "
        movie_images.adapter = MovieInfosAdapter(movieDetails.moviesInfo)

    }
}