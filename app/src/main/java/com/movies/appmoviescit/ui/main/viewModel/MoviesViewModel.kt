package com.movies.appmoviescit.ui.main.viewModel

import androidx.lifecycle.MutableLiveData
import com.movies.appmoviescit.model.*
import com.movies.appmoviescit.services.MoviesConstants
import com.movies.appmoviescit.services.NetworkState
import com.movies.appmoviescit.services.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel {

    enum class MOVIE_TYPE {
        POPULAR, UPCOMING
    }

    var popularMovies = MutableLiveData<List<MovieItem>>()
    var popularMoviesList = ArrayList<MovieItem>()
    var upcomingMovies = MutableLiveData<List<MovieItem>>()
    var upcomingMoviesList = ArrayList<MovieItem>()

    var movieDetailComplete = MutableLiveData<MovieDetailComplete>()
    var networkState = MutableLiveData<NetworkState>()
    var error = MutableLiveData<String>()

    fun loadMovies(movieType: MOVIE_TYPE, page: Int = 1) {
        if (movieType == MOVIE_TYPE.POPULAR) {
            getPopularMovies(page)
        } else {
            getUpcomingMovies(page)
        }
    }

    private fun getPopularMovies(page: Int = 1) {
        networkState.postValue(NetworkState.RUNNING)

        val call = RetrofitConfig().movieService().popularMovies(MoviesConstants.API_KEY, MoviesConstants.LANGUAGE, page)
        call.enqueue(object: Callback<MovieResults?> {
            override fun onResponse(call: Call<MovieResults?>?,
                                    response: Response<MovieResults?>?) {
                response?.body()?.let {
                    popularMoviesList.addAll(it.results)
                    popularMovies.postValue(popularMoviesList)
                    networkState.postValue(NetworkState.SUCCESS)
                }
            }

            override fun onFailure(call: Call<MovieResults?>?,
                                   t: Throwable?) {
                networkState.postValue(NetworkState.ERROR)
            }
        })
    }

    private fun getUpcomingMovies(page: Int = 1) {
        networkState.postValue(NetworkState.RUNNING)

        val call = RetrofitConfig().movieService().upcomingrMovies(MoviesConstants.API_KEY, MoviesConstants.LANGUAGE, page)
        call.enqueue(object: Callback<MovieResults?> {
            override fun onResponse(call: Call<MovieResults?>?,
                                    response: Response<MovieResults?>?) {
                response?.body()?.let {
                    upcomingMoviesList.addAll(it.results)
                    upcomingMovies.postValue(upcomingMoviesList)
                    networkState.postValue(NetworkState.SUCCESS)
                }
            }

            override fun onFailure(call: Call<MovieResults?>?,
                                   t: Throwable?) {
                error.postValue("ERROR")
                networkState.postValue(NetworkState.ERROR)
            }
        })
    }

    fun getMovieDetail(movieId: String) {
        networkState.postValue(NetworkState.RUNNING)

        val call = RetrofitConfig().movieService().movieDetail(movieId, MoviesConstants.API_KEY)
        call.enqueue(object: Callback<MovieDetail?> {
            override fun onResponse(call: Call<MovieDetail?>, response: Response<MovieDetail?>) {
                response?.body()?.let {
                    getMovieCast(movieId, it)
                }
            }

            override fun onFailure(call: Call<MovieDetail?>, t: Throwable) {
                error.postValue("ERROR")
                networkState.postValue(NetworkState.ERROR)
            }
        })
    }

    private fun getMovieCast(movieId: String, movieDetail: MovieDetail) {
        val call = RetrofitConfig().movieService().credits(movieId, MoviesConstants.API_KEY)
        call.enqueue(object: Callback<CastResults?>{
            override fun onResponse(call: Call<CastResults?>, response: Response<CastResults?>) {
                response?.body()?.let {
                    val itemComplete = MovieDetailComplete(movieDetail, it)
                    movieDetailComplete.postValue(itemComplete)
                    networkState.postValue(NetworkState.SUCCESS)
                }
            }

            override fun onFailure(call: Call<CastResults?>, t: Throwable) {
                networkState.postValue(NetworkState.ERROR)
            }
        })
    }
}