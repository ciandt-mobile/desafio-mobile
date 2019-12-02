package br.com.josericardooliveira.desafiomobile.repository.data

import androidx.paging.PageKeyedDataSource
import br.com.josericardooliveira.desafiomobile.model.MovieInfo
import br.com.josericardooliveira.desafiomobile.model.MovieQuery
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PopularMoviesDataSource : PageKeyedDataSource<Int, MovieInfo>() {

    private val service: TmdbDataService = TmdbDataRepository.getService()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieInfo>
    ) {
        val movieList = service.popularMovies(1)
        val response = movieList.execute()
        if(response.isSuccessful) {
            val movies = response.body()?.results?.toMutableList() ?: mutableListOf()
            callback.onResult(movies, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieInfo>) {
        val movieList = service.popularMovies(params.key)
        val response = movieList.execute()
        if(response.isSuccessful) {
            val movies = response.body()?.results?.toMutableList() ?: mutableListOf()
            callback.onResult(movies,  params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieInfo>) {

    }
}