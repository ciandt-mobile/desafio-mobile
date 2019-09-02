/*
 * MoviesFragmentViewModel.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 03:18
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.home.viewModel

import br.com.codigozeroum.desafiomobile.R
import br.com.codigozeroum.desafiomobile.features.home.model.ResultItem
import br.com.codigozeroum.desafiomobile.features.home.model.repository.MoviesRepository
import br.com.codigozeroum.desafiomobile.projectStructure.BaseViewModel
import br.com.codigozeroum.desafiomobile.projectStructure.RecyclerViewDataSource
import br.com.codigozeroum.desafiomobile.projectStructure.ViewModelState

class MoviesFragmentViewModel: BaseViewModel(), RecyclerViewDataSource<ResultItem> {

    private val repository = MoviesRepository()
    var results: MutableList<ResultItem> = mutableListOf()
    var totalPages = 1

    fun getUpcomingMovies(page: Int = 1){

        val disposable = repository.getUpcomingMovies(page)
            .subscribe({result ->

                if(result.results != null){

                    totalPages =  result.total_pages!!
                    results = result.results

                    postNewState(ViewModelState.Success)
                }else{
                    postNewState(ViewModelState.Error)
                }
            },{
                postNewState(ViewModelState.Error)
            })
        addToDisposeBag(disposable)
    }




    fun getTopRatedMovies(page: Int = 1){

        val disposable = repository.getTopRatedMovies(page)
            .subscribe({result ->

                if(result.results != null){

                    totalPages =  result.total_pages!!
                    results = result.results

                    postNewState(ViewModelState.Success)
                }else{
                    postNewState(ViewModelState.Error)
                }
            },{
                postNewState(ViewModelState.Error)
            })
        addToDisposeBag(disposable)
    }

    fun getPopularMovies(page: Int = 1){

        val disposable = repository.getPopularMovies(page)
            .subscribe({result ->

                if(result.results != null){

                    totalPages =  result.total_pages!!
                    results = result.results

                    postNewState(ViewModelState.Success)
                }else{
                    postNewState(ViewModelState.Error)
                }
            },{
                postNewState(ViewModelState.Error)
            })
        addToDisposeBag(disposable)
    }


    override fun getItemCount(): Int = results.size
    override fun getViewTypeFor(position: Int): Int = R.layout.item_movie_grid //não utilizado por causa do inflater do Adapter
    override fun getItemFor(position: Int): ResultItem  = results[position]

}