package br.com.josericardooliveira.desafiomobile.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import br.com.josericardooliveira.desafiomobile.model.MovieInfo
import br.com.josericardooliveira.desafiomobile.repository.data.PopularMoviesDataSourceFactory
import br.com.josericardooliveira.desafiomobile.repository.data.UpcomingMoviesDataSourceFactory
import java.util.concurrent.Executors


class MoviesViewModel : ViewModel() {
    val popularMoviesList: LiveData<PagedList<MovieInfo>>
    val upcomingMoviesList: LiveData<PagedList<MovieInfo>>
    val isUpcoming: MutableLiveData<Boolean>

    init {
        val factoryPopularMovies = PopularMoviesDataSourceFactory()
        val factoryUpcomingMovies = UpcomingMoviesDataSourceFactory()

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .build()

        val executor = Executors.newFixedThreadPool(5)

        isUpcoming = MutableLiveData()
        isUpcoming.postValue(true)

        popularMoviesList = LivePagedListBuilder<Int, MovieInfo>(factoryPopularMovies, config)
                .setFetchExecutor(executor)
                .build()

        upcomingMoviesList = LivePagedListBuilder<Int, MovieInfo> (factoryUpcomingMovies, config)
            .setFetchExecutor(executor)
            .build()
    }

    fun toggleUpcoming(value: Boolean) {
        isUpcoming.postValue(value)
        print("oioioi")
    }
}
