package com.nurik.desafiomobile.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object Coroutines {
    fun<T: Any> ioThenMain(work: suspend (() -> T?), callback: ((T?)->Unit)) =
            CoroutineScope(Dispatchers.Main).launch {
                val data= CoroutineScope(Dispatchers.Main).async rt@{
                    return@rt work()
                }.await()
                callback(data)
            }
}