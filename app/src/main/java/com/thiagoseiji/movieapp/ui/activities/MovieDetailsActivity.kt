package com.thiagoseiji.movieapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.thiagoseiji.movieapp.R
import com.thiagoseiji.movieapp.viewmodel.MovieDetailViewModel
import kotlinx.android.synthetic.main.activity_movie_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat

class MovieDetailsActivity : AppCompatActivity() {

    private val movieDetailsVM: MovieDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_details)

        val id = intent.getIntExtra("id", 0)

        movieDetailsVM.getMovie(id).observe(this, Observer { data ->
            if (data != null) {

                val yearFormat = SimpleDateFormat("yyyy")

                movie_details_title.text = data.title
                movie_details_year.text = yearFormat.format(data.releaseDate)
                movie_details_overview.text = data.overview

                movie_details_duration_and_genre.text = "${data.duration}m | Animation"

                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/${data.backdropImage}")
                    .centerCrop()
                    .into(movie_details_header)
            }

        })

    }

}
