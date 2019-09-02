/*
 * SearchMovieViewModel.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 02:53
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.home.viewModel

import br.com.codigozeroum.desafiomobile.R
import br.com.codigozeroum.desafiomobile.features.home.model.ResultItem
import br.com.codigozeroum.desafiomobile.features.home.model.repository.SearchMoviesRepository
import br.com.codigozeroum.desafiomobile.projectStructure.BaseViewModel
import br.com.codigozeroum.desafiomobile.projectStructure.RecyclerViewDataSource
import br.com.codigozeroum.desafiomobile.projectStructure.ViewModelState

class SearchMovieViewModel : BaseViewModel(), RecyclerViewDataSource<ResultItem> {

    private val repository = SearchMoviesRepository()
    var results: MutableList<ResultItem> = mutableListOf()
    var totalPages = 1

    fun searchMovie(query: String, page: Int = 1){

        val disposable = repository.searchMovie(query, page)
            .subscribe({result->
                if(result != null){
                    totalPages = result.total_pages!!
                    results = result.results!!
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
