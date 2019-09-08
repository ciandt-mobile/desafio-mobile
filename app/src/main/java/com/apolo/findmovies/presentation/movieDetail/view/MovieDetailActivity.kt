package com.apolo.findmovies.presentation.movieDetail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apolo.findmovies.R
import com.apolo.findmovies.data.model.MovieViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_MOVIE_DETAILS = "extra_movie_details"

        fun getStartIntent(context: Context, movieDetail : MovieViewModel) = Intent(context, MovieDetailActivity::class.java).apply {
            this.putExtra(EXTRA_MOVIE_DETAILS, movieDetail)
        }
    }

    private val movieDetails : MovieViewModel by lazy {
        intent.extras.get(EXTRA_MOVIE_DETAILS) as MovieViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        Picasso.with(this).load(movieDetails.bannerImage).into(main_movie_image)
        movie_name.text = movieDetails.name
        movie_duration.text = movieDetails.duration
        movie_category.text = movieDetails.genreIds.toString()
//        movie_images.adapter = MovieInfosAdapter(movieDetails.moviesInfo)

        movie_description.text = movieDetails.overview

    }
}