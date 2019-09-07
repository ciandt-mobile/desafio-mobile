package com.apolo.findmovies.presentation.movieDetail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apolo.findmovies.R

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MovieDetailActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

    }
}