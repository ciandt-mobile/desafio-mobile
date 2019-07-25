package com.thiagoseiji.movieapp.repository

import androidx.lifecycle.LiveData
import com.thiagoseiji.movieapp.data.api.MovieResponse
import com.thiagoseiji.movieapp.retrofit.MovieService

class MovieRepository(service: MovieService) : BaseRepository<MovieResponse>(service) {

    override fun loadData(): LiveData<MovieResponse> {
       return fetchData { service.getMoviewsAsync() }
    }


}