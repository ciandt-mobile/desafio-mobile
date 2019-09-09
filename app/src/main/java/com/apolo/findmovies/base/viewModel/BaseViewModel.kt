package com.apolo.findmovies.data.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

open class BaseViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext = Dispatchers.Default

    private val jobs = ArrayList<Job>()

    fun runCoroutine(run: suspend () -> Unit) : ExceptionDriver {
        return ExceptionDriver(run)
    }

    override fun onCleared() {
        super.onCleared()
        jobs.forEach { if(!it.isCancelled) it.cancel() }
    }

    class ExceptionDriver(val action: suspend () -> Unit)

    infix fun ExceptionDriver.onError(onError: (Exception) -> Unit) {
        initializeCoroutine(action, onError)
    }

    private fun initializeCoroutine(run: suspend () -> Unit, onError: (Exception) -> Unit) = jobs.add(launch {
        try {
            run()
        } catch (e: Exception) {
            onError(e)
        }
    })


}