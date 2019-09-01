/*
 * MovieDetailViewModel.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 01/09/19 03:46
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.movie_details

import br.com.codigozeroum.desafiomobile.features.home.model.repository.MoviesRepository
import br.com.codigozeroum.desafiomobile.features.movie_details.model.MovieDetail
import br.com.codigozeroum.desafiomobile.projectStructure.BaseViewModel
import br.com.codigozeroum.desafiomobile.projectStructure.ViewModelState

class MovieDetailViewModel: BaseViewModel() {

    private val repository = MovieDetailRepository()
    lateinit var movieDetail: MovieDetail

    fun getMovieDetail(movieId: Int){

        val disposable = repository.getMovieDetail(movieId)
            .subscribe({result ->

                if(result != null){
                    movieDetail = result
                    movieDetail.poster_path = "https://image.tmdb.org/t/p/w1280${movieDetail.poster_path}"
                    postNewState(ViewModelState.Success)
                }else{
                    postNewState(ViewModelState.Error)
                }

            },{
                postNewState(ViewModelState.Error)
            })
        addToDisposeBag(disposable)
    }
}