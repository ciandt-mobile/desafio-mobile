package com.apolo.findmovies.presentation.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.apolo.findmovies.R
import com.apolo.findmovies.base.model.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
            Toast.makeText(this, it.name, Toast.LENGTH_LONG).show()
        }
    }
}
