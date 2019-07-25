package com.thiagoseiji.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thiagoseiji.movieapp.retrofit.MovieService
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

abstract class BaseRepository<T>(@PublishedApi internal val service: MovieService) {

    inline fun <reified T: Any> fetchData(crossinline call: (MovieService) -> Deferred<Response<T>>): MutableLiveData<T> {
        val result = MutableLiveData<T>()

        CoroutineScope(Dispatchers.IO).launch {
            val request = call(service)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        result.value = response.body()
                    } else {
                        Timber.d("Error ocurred with code ${response.code()}")
                    }
                } catch (e: HttpException) {
                    Timber.d("Error ${e.message()}")
                } catch (e: Throwable){
                    Timber.d("Error ${e.message}")
                }
            }
        }

        return result
    }
}