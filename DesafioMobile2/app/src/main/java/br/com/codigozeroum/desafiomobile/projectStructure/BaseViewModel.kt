/*
 * BaseViewModel.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 30/08/19 05:37
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.projectStructure

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val state = MutableLiveData<ViewModelState>()

    fun postNewState(newState: ViewModelState) {
        state.postValue(newState)
    }

    fun register(lifecycleOwner: LifecycleOwner, observer: Observer<ViewModelState>) {
        state.observe(lifecycleOwner, observer)
    }

    fun addToDisposeBag(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun disposeViewModelCalls() {
        compositeDisposable.dispose()
    }

    override fun onCleared() {
        super.onCleared()
        disposeViewModelCalls()
    }
}