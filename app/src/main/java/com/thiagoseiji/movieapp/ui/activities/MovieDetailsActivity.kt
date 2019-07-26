package com.thiagoseiji.movieapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.thiagoseiji.movieapp.R
import com.thiagoseiji.movieapp.ui.adapters.CastAdapter
import com.thiagoseiji.movieapp.viewmodel.MovieDetailViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat

class MovieDetailsActivity : AppCompatActivity() {

    private val movieDetailsVM: MovieDetailViewModel by viewModel()

    private val castAdapter: CastAdapter by lazy { CastAdapter(mutableListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_details)

        val id = intent.getIntExtra("id", 0)

        movie_details_cast_rv.adapter = castAdapter
        movie_details_cast_rv.layoutManager = GridLayoutManager(this, 3)

        movieDetailsVM.getMovie(id).observe(this, Observer { data ->
            if (data != null) {

                val yearFormat = SimpleDateFormat("yyyy")

                movie_details_title.text = data.title
                movie_details_year.text = yearFormat.format(data.releaseDate)
                movie_details_overview.text = data.overview

                val commaSeperatedString = data.genre.joinToString { it -> "${it.name}" }

                movie_details_duration_and_genre.text = "${data.duration}m | $commaSeperatedString"

                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w300/${data.backdropImage}")
                    .centerCrop()
                    .into(movie_details_header)
            }

        })

        movieDetailsVM.getCast(id).observe(this, Observer { data ->
            if (data != null) {
                castAdapter.updateCastList(data.results.subList(0,3))
            }

        })

    }

}
