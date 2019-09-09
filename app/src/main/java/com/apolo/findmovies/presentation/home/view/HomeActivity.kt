package com.apolo.findmovies.presentation.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.apolo.findmovies.R
import com.apolo.findmovies.base.resources.Resource
import com.apolo.findmovies.data.model.MovieViewModel
import com.apolo.findmovies.presentation.home.viewModel.HomeViewModel
import com.apolo.findmovies.presentation.movieDetail.view.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by inject()

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        moviesAdapter = MoviesAdapter(mutableListOf()) { openMovieDetail(it) }

        movies_list.adapter = moviesAdapter

        homeViewModel.getMoviesLiveData().observe(this, Observer { resource ->
            when(resource?.status) {
                Resource.Status.SUCCESS -> {
                    resource.data?.let {moviesList ->
                        moviesAdapter.setMovies(moviesList)
                    }
                }
                Resource.Status.LOADING -> {

                }
                else -> {

                }
            }
        })

        category_button.setOnCheckedChangeListener { radioGroup, p1 ->
            homeViewModel.onCategoryChange(radioGroup?.checkedRadioButtonId == upcoming_option.id)
            if (radioGroup?.checkedRadioButtonId == upcoming_option.id) {
                selected_option_title.text = resources.getString(R.string.selected_movie_title, getText(R.string.upcoming_option_title))
            } else {
                selected_option_title.text = resources.getString(R.string.selected_movie_title, getText(R.string.popular_option_title))
            }
        }

        homeViewModel.onViewReady()
    }

    private fun openMovieDetail(movieViewModel: MovieViewModel) : Unit {
        startActivity(
            MovieDetailActivity.getStartIntent(this, movieViewModel)
        )

       /* MovieDetailViewModel(
            "",
            "Titanic",
            "13/02",
            "196m",
            "Science Fiction, Action, Drama",
            "Blalblaslbasob f msa mbpsam bsomb pasmbp omasp mbpsam pamspo mpsam bpoasm pbomaspom b",
            listOf(
                MovieInfoViewModel("", "Apolo", "Herói"),
                MovieInfoViewModel("", "Leonardo", "Herói"),
                MovieInfoViewModel("", "Luís", "Herói"),
                MovieInfoViewModel("", "Henrique", "Herói"),
                MovieInfoViewModel("", "Tássio", "Herói"),
                MovieInfoViewModel("", "Toníssio", "Herói"),
                MovieInfoViewModel("", "Jefferson", "Herói")
            )
        )*/
    }
}
