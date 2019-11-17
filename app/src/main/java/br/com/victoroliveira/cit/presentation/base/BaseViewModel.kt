package br.com.victoroliveira.cit.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.victoroliveira.cit.data.model.ErrorModel
import br.com.victoroliveira.cit.data.remote.ApiErrorHandle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class BaseViewModel : ViewModel() {

    val scope = CoroutineScope(
        Job() + Dispatchers.Main
    )

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

    fun getError(e: Exception) : ErrorModel {
        return ApiErrorHandle.traceErrorException(e)
    }

    fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }

}