package com.msaviczki.themovieapp.helper.extension

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun <T : Any> safeRequestCall(call: suspend () -> com.msaviczki.themovieapp.network.core.Result<T>): com.msaviczki.themovieapp.network.core.Result<T> = try {
    call.invoke()
}
catch (e: Throwable) {
    com.msaviczki.themovieapp.network.core.Result.Error("Erro ao fazer requisição")
}

fun delay(milliseconds: Long, action: () -> Unit) {
    GlobalScope.launch(Dispatchers.Main) {
        delay(milliseconds)
        action.invoke()
    }
}