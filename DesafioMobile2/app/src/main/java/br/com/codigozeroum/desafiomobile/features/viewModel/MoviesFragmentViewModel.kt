/*
 * MoviesFragmentViewModel.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 03:18
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.viewModel

import br.com.codigozeroum.desafiomobile.features.model.PopularResponse
import br.com.codigozeroum.desafiomobile.features.model.repository.MoviesRepository
import br.com.codigozeroum.desafiomobile.projectStructure.BaseViewModel
import br.com.codigozeroum.desafiomobile.projectStructure.ViewModelState

class MoviesFragmentViewModel: BaseViewModel() {

    private val repository = MoviesRepository()
    lateinit var response: PopularResponse

    fun getUpacomingMovies(){

        val disposable = repository.getUpcomingMovies()
            .subscribe({result ->

                if(result.results != null){
                    response =  result
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