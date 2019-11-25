package com.adelannucci.movies.data.remote

import android.util.Log
import com.adelannucci.movies.data.ApiTheMovieService
import com.adelannucci.movies.data.remote.response.BaseResponse
import com.adelannucci.movies.data.remote.response.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TheMovieDataSourceImpl(private val api: ApiTheMovieService) : TheMovieDataSource {

    companion object {
        val TAG: String = "TheMovieDataSourceImpl"
    }

    override fun getMostPopularMovies(
        page: Int,
        languageCode: String,
        success: (List<MovieResponse>) -> Unit,
        failure: () -> Unit
    ) {
        val fetched = api.getDiscoverMovie(page, languageCode)
        fetched.enqueue(object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable?) {
                failure()
            }

            override fun onResponse(
                call: Call<BaseResponse>?,
                response: Response<BaseResponse>
            ) {

                if (response.isSuccessful) {
                    response?.body()?.let {
                        val data = it
                        val movies = data.results
                        Log.d(TAG, "onResponse list of Movies: $movies")
                        success(movies)
                    }
                } else {
                    failure()
                }

            }
        })


    }
}

//override suspend fun fetch(page: Int, languageCode: String) {
//    try {
//        val fetched = api.getDiscoverMovie(page, languageCode)
//        _downloadedMovies.postValue(fetched)
//    } catch (e: NoConnectivityException) {
//        Log.e("Connectivity", "No internet connection.", e)
//    }
//}
//}