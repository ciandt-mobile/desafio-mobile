package br.com.architerure.movies.app.viewModel

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.architerure.movies.api.MoviesService
import br.com.architerure.movies.api.model.CastResponse
import br.com.architerure.movies.api.model.GenreResponse
import br.com.architerure.movies.api.model.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviesViewModel(appApplication: android.app.Application) : AndroidViewModel(appApplication) {

    private val api = MoviesService.instance(appApplication)

    val allMovies: MutableLiveData<MoviesResponse> = MutableLiveData()
    val allGenres: MutableLiveData<GenreResponse> = MutableLiveData()
    val allCast: MutableLiveData<CastResponse> = MutableLiveData()

    fun popularMovies() {
        val call = api.apiInterface().popularMovies("3fa9058382669f72dcb18fb405b7a831", "1", "en-US")
        call.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful)
                    allMovies.value = response.body()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                allMovies.value = null
                Log.i("Teste ", "onFailure")
            }
        })
    }


    fun upComingMovies() {
        val call = api.apiInterface().upComingMovies("3fa9058382669f72dcb18fb405b7a831", "1", "en-US")
        call.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful)
                    allMovies.value = response.body()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                allMovies.value = null
                Log.i("Teste ", "onFailure")
            }
        })
    }

    fun genreList() {
        if (allGenres.value != null) return
        val call = api.apiInterface().genres("3fa9058382669f72dcb18fb405b7a831")
        call.enqueue(object : Callback<GenreResponse> {
            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                if (response.isSuccessful)
                    allGenres.value = response.body()
            }

            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                allGenres.value = null
                Log.i("Teste ", "onFailure")
            }
        })
    }

    fun castList(idMovie: Int) {
        if (allCast.value != null) return
        val call = api.apiInterface().castMovie(idMovie, "3fa9058382669f72dcb18fb405b7a831")
        call.enqueue(object : Callback<CastResponse> {
            override fun onResponse(call: Call<CastResponse>, response: Response<CastResponse>) {
                if (response.isSuccessful)
                    allCast.value = response.body()
            }

            override fun onFailure(call: Call<CastResponse>, t: Throwable) {
                allCast.value = null
                Log.i("Teste ", "onFailure")
            }
        })
    }

}