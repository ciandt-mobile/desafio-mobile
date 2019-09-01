/*
 * MovieDetailActivity.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 01/09/19 03:44
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.movie_details

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.codigozeroum.desafiomobile.R
import br.com.codigozeroum.desafiomobile.projectStructure.BaseActivity
import br.com.codigozeroum.desafiomobile.projectStructure.ViewModelState
import br.com.codigozeroum.desafiomobile.utils.ImageLoader
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : BaseActivity() {

    lateinit var viewModel: MovieDetailViewModel
    var movieId = 429203

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        configureView()
        configureViewModel()
    }

    override fun configureView() {
        backButton.setOnClickListener { this.finish() }
    }

    override fun configureViewModel() {
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)

        viewModel.register(this, Observer { newState->
            when(newState){
                ViewModelState.Success -> {
                    bindView()
                }
            }
        })
        movieId = intent.getIntExtra("movieId", 429203)
        viewModel.getMovieDetail(movieId)
    }

    override fun bindView() {

        ImageLoader.loadImageWith(this, viewModel.movieDetail.poster_path, poster)
        movieTitle.text = viewModel.movieDetail.title
        year.text = viewModel.movieDetail.release_date.substring(0, 4)
        var genres = ""
        viewModel.movieDetail.genres.forEach {
            genres += "${it.name}, "
        }
        if(viewModel.movieDetail.genres.isNotEmpty()){
            genres =  genres.substring(0, genres.length -2)
        }
        othersInfo.text = "${viewModel.movieDetail.runtime}min | $genres"
        sinopse.text = viewModel.movieDetail.overview
    }

}
