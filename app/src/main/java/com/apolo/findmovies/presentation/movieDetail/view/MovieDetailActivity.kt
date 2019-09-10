package com.apolo.findmovies.presentation.movieDetail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.apolo.findmovies.R
import com.apolo.findmovies.base.getYear
import com.apolo.findmovies.base.resources.Resource
import com.apolo.findmovies.data.model.MovieViewModel
import com.apolo.findmovies.presentation.movieDetail.viewModel.MovieDetailViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.android.ext.android.inject

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_MOVIE_DETAILS = "extra_movie_details"

        fun getStartIntent(context: Context, movieDetail : MovieViewModel) = Intent(context, MovieDetailActivity::class.java).apply {
            this.putExtra(EXTRA_MOVIE_DETAILS, movieDetail)
        }
    }

    private val viewModel : MovieDetailViewModel by inject()

    private val movieDetails : MovieViewModel by lazy {
        intent.extras.get(EXTRA_MOVIE_DETAILS) as MovieViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        Picasso.with(this).load(movieDetails.bannerImage).into(main_movie_image, object : Callback {
            override fun onSuccess() {
                loader.visibility = View.GONE
            }

            override fun onError() {
                main_movie_image.setImageResource(R.drawable.movie_error_state)
                loader.visibility = View.GONE
            }
        })

        movie_name.text = movieDetails.name
        movie_duration.text = movieDetails.duration
        movie_category.text = movieDetails.genreIds.toString()

        movie_year.text = movieDetails.releaseDate.getYear().toString()

        movie_description.text = movieDetails.overview


        viewModel.getGenresLiveData().observe(this, Observer {
            when(it.status) {
                Resource.Status.SUCCESS -> {
                    movie_category.text = it?.data
                } else -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        viewModel.getCreditsLiveData().observe(this, Observer {
            it?.data?.let {
                movie_images.adapter = MovieInfosAdapter(it)
            }
        })

        viewModel.onViewReady(movieDetails)

    }
}