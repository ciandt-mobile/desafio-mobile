/*
 * MoviesFragmentViewModel.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 03:18
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.viewModel

import br.com.codigozeroum.desafiomobile.R
import br.com.codigozeroum.desafiomobile.features.model.MoviesResponse
import br.com.codigozeroum.desafiomobile.features.model.ResultItem
import br.com.codigozeroum.desafiomobile.features.model.repository.MoviesRepository
import br.com.codigozeroum.desafiomobile.projectStructure.BaseViewModel
import br.com.codigozeroum.desafiomobile.projectStructure.RecyclerViewDataSource
import br.com.codigozeroum.desafiomobile.projectStructure.ViewModelState

class MoviesFragmentViewModel: BaseViewModel(), RecyclerViewDataSource<ResultItem> {

    private val repository = MoviesRepository()
    //lateinit var response: MoviesResponse
    var results: MutableList<ResultItem> = mutableListOf()

    fun getUpacomingMovies(){

        val disposable = repository.getUpcomingMovies()
            .subscribe({result ->

                if(result.results != null){

                    //response =  result
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