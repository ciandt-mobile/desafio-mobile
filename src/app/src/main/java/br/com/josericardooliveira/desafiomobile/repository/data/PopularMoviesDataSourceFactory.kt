package br.com.josericardooliveira.desafiomobile.repository.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import br.com.josericardooliveira.desafiomobile.model.MovieInfo

class PopularMoviesDataSourceFactory : DataSource.Factory<Int, MovieInfo>() {
    val data: MutableLiveData<PopularMoviesDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, MovieInfo> {
        val dataSource = PopularMoviesDataSource()
        data.postValue(PopularMoviesDataSource())
        return dataSource
    }
}