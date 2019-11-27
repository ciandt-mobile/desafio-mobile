package com.adelannucci.movies.view.view.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.adelannucci.movies.R
import com.adelannucci.movies.data.ApiTheMovieService
import com.adelannucci.movies.data.MoviesRepository
import com.adelannucci.movies.data.remote.ConnectivityInterceptorImpl
import com.adelannucci.movies.data.remote.TheMovieDataSourceImpl
import com.adelannucci.movies.view.viewmodel.details.MovieDetailsViewModel
import android.databinding.DataBindingUtil
import android.widget.ImageView
import com.adelannucci.movies.databinding.ActivityMovieDetailsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieDetailsActivity : AppCompatActivity() {

    var viewModel: MovieDetailsViewModel? = null
    var id: Long = 0
    var activityMovieDetailsBinding: ActivityMovieDetailsBinding? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiService = ApiTheMovieService(ConnectivityInterceptorImpl(applicationContext))
        val dataSource = TheMovieDataSourceImpl(apiService)
        val repository = MoviesRepository(dataSource)

        activityMovieDetailsBinding = DataBindingUtil.setContentView(
            this, R.layout
                .activity_movie_details
        )

        viewModel = MovieDetailsViewModel(repository, findViewById(R.id.image_photo))
        activityMovieDetailsBinding?.viewModel = viewModel
//        activityMovieDetailsBinding?.casts = viewModel?.casts.toString()
        id = intent.getLongExtra("MOVIE_ID", 1)

    }

    override fun onResume() {
        super.onResume()
        viewModel?.load(id)

    }



}
