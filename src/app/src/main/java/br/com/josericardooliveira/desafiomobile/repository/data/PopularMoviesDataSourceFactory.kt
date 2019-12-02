package br.com.josericardooliveira.desafiomobile.repository.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import br.com.josericardooliveira.desafiomobile.model.MovieInfo

class PopularMoviesDataSourceFactory: DataSource.Factory<Int, MovieInfo>() {
    private val dataSource: PopularMoviesDataSource = PopularMoviesDataSource()
    val data: MutableLiveData<PopularMoviesDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, MovieInfo> {
        data.postValue(dataSource)
        return dataSource
    }
}