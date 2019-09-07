package com.apolo.findmovies.presentation.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apolo.findmovies.R
import com.apolo.findmovies.base.model.MovieViewModel
import com.apolo.findmovies.presentation.movieDetail.view.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        movies_list.adapter = MoviesAdapter(listOf(
            MovieViewModel("","Era um vez", "10/12/2020"),
            MovieViewModel("","Era um vez", "10/12/2020"),
            MovieViewModel("","Era um vez", "10/12/2020"),
            MovieViewModel("","Era um vez", "10/12/2020"),
            MovieViewModel("","Era um vez", "10/12/2020"),
            MovieViewModel("","Era um vez", "10/12/2020"),
            MovieViewModel("","Era um vez", "10/12/2020"),
            MovieViewModel("","Era um vez", "10/12/2020"),
            MovieViewModel("","Era um vez", "10/12/2020"),
            MovieViewModel("","Era um vez", "10/12/2020"),
            MovieViewModel("","Era um vez", "10/12/2020"),
            MovieViewModel("","Era um vez", "10/12/2020"),
            MovieViewModel("","Era um vez", "10/12/2020"),
            MovieViewModel("","Era um vez", "10/12/2020"),
            MovieViewModel("","Era um vez", "10/12/2020")
        )) {
            startActivity(MovieDetailActivity.getStartIntent(this))
        }
    }
}
