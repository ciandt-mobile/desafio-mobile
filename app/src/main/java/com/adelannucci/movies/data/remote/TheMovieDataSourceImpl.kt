package com.adelannucci.movies.data.remote

import android.util.Log
import com.adelannucci.movies.data.ApiTheMovieService
import com.adelannucci.movies.data.remote.response.BaseResponse
import com.adelannucci.movies.data.remote.response.MovieDetailsResponse
import com.adelannucci.movies.data.remote.response.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TheMovieDataSourceImpl(private val api: ApiTheMovieService) : TheMovieDataSource {

    companion object {
        val TAG: String = "TheMovieDataSourceImpl"
    }

    override fun getMovies(
        filter: String,
        page: Int,
        language: String,
        success: (List<MovieResponse>) -> Unit,
        failure: () -> Unit
    ) {
        val fetched = api.getMovies(filter, page, language)
        fetched.enqueue(object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable?) {
                failure()
            }

            override fun onResponse(
                call: Call<BaseResponse>?,
                response: Response<BaseResponse>
            ) {

                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        Log.d(TAG, "page: ${data.page}")
                        Log.d(TAG, "pages: ${data.totalPages}")
                        Log.d(TAG, "results: ${data.totalResults}")
                        Log.d(TAG, "movies: ${data.results}")
                        success(data.results)
                    }
                } else {
                    failure()
                }
            }
        })
    }

    override fun getMovie(
        id: Long,
        language: String,
        success: (MovieDetailsResponse) -> Unit,
        failure: () -> Unit
    ) {
        val fetched = api.getMovie(id, language, "casts")
        fetched.enqueue(object : Callback<MovieDetailsResponse> {
            override fun onFailure(call: Call<MovieDetailsResponse>, t: Throwable?) {
                failure()
            }

            override fun onResponse(
                call: Call<MovieDetailsResponse>?,
                response: Response<MovieDetailsResponse>
            ) {

                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        Log.d(TAG, "movie: ${data}")
                        success(data)
                    }
                } else {
                    failure()
                }
            }
        })
    }
}