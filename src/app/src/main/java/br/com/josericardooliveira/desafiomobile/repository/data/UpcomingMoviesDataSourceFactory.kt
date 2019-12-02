package br.com.josericardooliveira.desafiomobile.repository.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import br.com.josericardooliveira.desafiomobile.model.MovieInfo

class UpcomingMoviesDataSourceFactory: DataSource.Factory<Int, MovieInfo>() {
    private val dataSource = UpcomingMoviesDataSource()
    val data: MutableLiveData<UpcomingMoviesDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, MovieInfo> {
        data.postValue(dataSource)
        return dataSource
    }
}