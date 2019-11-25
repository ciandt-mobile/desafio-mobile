package br.com.suelen.mobilechallenge.utilities

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import br.com.suelen.mobilechallenge.data.model.Result.Error

class NetworkUtil {

    companion object {
        fun parseRetrofitException(t : Throwable) : Error {
            return when(t) {
                is HttpException -> {
                    when(t.code()) {
                        404 -> Error("Not found")
                        401 -> Error("Unauthorized")
                        500 -> Error("Service unavailable")
                        else -> Error("Unknown error")
                    }
                }
                is SocketTimeoutException -> {
                    Error("Server timeout")
                }
                is IOException -> {
                    Error("No connection")
                }
                else -> Error("Unknown error")
            }
        }
    }
}