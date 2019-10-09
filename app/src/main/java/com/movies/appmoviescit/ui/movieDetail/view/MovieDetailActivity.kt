package com.movies.appmoviescit.ui.movieDetail.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.appmoviescit.R
import com.movies.appmoviescit.model.MovieDetailComplete
import com.movies.appmoviescit.utils.MoviesImageBuilder
import kotlinx.android.synthetic.main.movie_item_detail.*
import java.time.LocalDate

class MovieDetailActivity: AppCompatActivity() {

    private lateinit var castImageAdapter: MovieCastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_item_detail)

        setupView()
    }

    private fun setupView() {
        backButton.setOnClickListener { onBackPressed() }

        if (intent != null && intent.extras != null) {
            if (intent.extras!!.containsKey("MOVIE_DETAIL")) {
                val movieDetailComplete = intent.getSerializableExtra("MOVIE_DETAIL") as? MovieDetailComplete

                movieDetailComplete?.let {
                    setLayout(it)
                }
            }
        }
    }

    private fun setLayout(movieDetailComplete: MovieDetailComplete) {
        val movieImageUrlBuilder = MoviesImageBuilder()
        val movieDetail = movieDetailComplete.movieDetail

        val releaseDate = LocalDate.parse(movieDetail.release_date)

        Glide.with(movie_detail)
            .load(movieDetail.backdrop_path?.let { src ->
                movieImageUrlBuilder.buildBackdropUrl(src)
            })
            .apply(RequestOptions().placeholder(R.drawable.ic_placeholder))
            .into(movie_image_detail)

        movie_name_detail.text = movieDetail.title
        movie_year_detail.text = releaseDate.year.toString()

        var geners = ArrayList<String>()
        for (genre in movieDetail.genres) {
            geners.add(genre.name)
        }
        movie_geners_detail.text = geners.joinToString(", ")

        movie_duration_detail.text = movieDetail.runtime + "m"
        movie_overview.text = movieDetail.overview

        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        castImageAdapter = MovieCastAdapter(this, movieDetailComplete.cast.cast)
        castImageAdapter.setHasStableIds(true)

        recycler_view_list_casting.layoutManager = layoutManager
        recycler_view_list_casting.adapter = castImageAdapter
        recycler_view_list_casting.setBackgroundResource(android.R.color.white)
    }

    override fun onBackPressed() {
        ActivityCompat.finishAfterTransition(this)
    }
}