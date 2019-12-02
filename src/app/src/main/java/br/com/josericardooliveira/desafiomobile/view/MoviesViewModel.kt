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

        val config = PagedList.Config.Builder()
                .setPageSize(20)
                .build()

        val executor = Executors.newFixedThreadPool(5)

        val factoryPopularMovies = PopularMoviesDataSourceFactory()
        val factoryUpcomingMovies = UpcomingMoviesDataSourceFactory()

        isUpcoming = MutableLiveData()
        isUpcoming.postValue(true)

        popularMoviesList = LivePagedListBuilder(factoryPopularMovies, config)
                .setFetchExecutor(executor)
                .build()

        upcomingMoviesList = LivePagedListBuilder(factoryUpcomingMovies, config)
                .setFetchExecutor(executor)
                .build()
    }

    fun toggleUpcoming(value: Boolean) {
        isUpcoming.postValue(value)
        if (value) {
            upcomingMoviesList.value?.dataSource?.invalidate()
        } else {
            popularMoviesList.value?.dataSource?.invalidate()
        }
    }
}
