package br.com.victoroliveira.cit.data.remote

import br.com.victoroliveira.cit.data.model.ErrorModel
import br.com.victoroliveira.cit.data.model.ErrorStatus
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ApiErrorHandle {
    companion object {

        fun traceErrorException(throwable: Throwable?): ErrorModel {
            val errorModel: ErrorModel? = when (throwable) {

                is HttpException -> {
                    if (throwable.code() == 401) {
                        ErrorModel(
                            throwable.message(),
                            throwable.code(),
                            ErrorStatus.UNAUTHORIZED
                        )
                    } else {
                        getHttpError(throwable.response()?.errorBody())
                    }
                }

                is SocketTimeoutException -> {
                    ErrorModel(throwable.message, ErrorStatus.TIMEOUT)
                }

                is IOException -> {
                    ErrorModel(throwable.message, ErrorStatus.NO_CONNECTION)
                }

                is UnknownHostException -> {
                    ErrorModel(throwable.message, ErrorStatus.NO_CONNECTION)
                }

                else -> null
            }
            return errorModel ?: ErrorModel(
                "No Defined Error!",
                0,
                ErrorStatus.BAD_RESPONSE
            )
        }

        private fun getHttpError(body: ResponseBody?): ErrorModel {
            return try {
                ErrorModel(body.toString(), 400, ErrorStatus.BAD_RESPONSE)
            } catch (e: Throwable) {
                e.printStackTrace()
                ErrorModel(message = e.message, errorStatus = ErrorStatus.NOT_DEFINED)
            }
        }
    }
}