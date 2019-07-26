package com.thiagoseiji.movieapp.ui.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.thiagoseiji.movieapp.R
import com.thiagoseiji.movieapp.data.Cast
import com.thiagoseiji.movieapp.data.Movie
import com.thiagoseiji.movieapp.data.api.CastResponse
import com.thiagoseiji.movieapp.ui.adapters.CastAdapter
import com.thiagoseiji.movieapp.util.ItemDecoratorColums
import com.thiagoseiji.movieapp.viewmodel.MovieDetailViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat

class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        val MOVIE_ID = "id"
    }

    private val movieDetailsVM: MovieDetailViewModel by viewModel()

    private val castAdapter: CastAdapter by lazy { CastAdapter(mutableListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_details)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        setupRecyclerView()

        val id = intent.getIntExtra(MOVIE_ID, 0)

        movieDetailsVM.getMovie(id).observe(this, Observer { data ->
            if (data != null) {
                onMovieLoaded(data)
            }
        })

        movieDetailsVM.getCast(id).observe(this, Observer { data ->
            if (data != null) {
              onCastLoaded(data)
            }
        })
    }

    fun onMovieLoaded(data: Movie){
        val yearFormat = SimpleDateFormat(getString(R.string.year_date_pattern))

        movie_details_title.text = data.title
        movie_details_year.text = yearFormat.format(data.releaseDate)
        movie_details_overview.text = data.overview

        val commaSeperatedString = data.genre.joinToString { it -> "${it.name}" }

        movie_details_duration_and_genre.text = "${data.duration}m | $commaSeperatedString"

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/${data.backdropImage}")
            .centerCrop()
            .into(movie_details_header)
    }

    fun onCastLoaded(data: CastResponse){
        castAdapter.updateCastList(data.results.subList(0, 3))
    }

    fun setupRecyclerView(){


        movie_details_cast_rv.adapter = castAdapter
        movie_details_cast_rv.layoutManager = GridLayoutManager(this, 3)

        movie_details_cast_rv.addItemDecoration(
            ItemDecoratorColums(
                resources.getInteger(R.integer.columns_divider),
                3
            )
        )
    }

}
