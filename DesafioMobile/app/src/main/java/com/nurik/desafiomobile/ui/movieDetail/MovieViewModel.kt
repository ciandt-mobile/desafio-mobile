package com.nurik.desafiomobile.ui.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nurik.desafiomobile.data.MoviesRepository
import com.nurik.desafiomobile.pojo.Genre
import com.nurik.desafiomobile.pojo.Movie
import com.nurik.desafiomobile.pojo.ResponseGenre
import com.nurik.desafiomobile.utils.Coroutines
import kotlinx.coroutines.Job

class MovieViewModel(private val repository: MoviesRepository) : ViewModel(){

    private lateinit var job: Job
    val genreValues = ArrayList<String>()

    private val mGenreMovies = MutableLiveData<List<Genre>>()
    private val GenreMovies: LiveData<List<Genre>>
        get() = mGenreMovies

    private val mMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie>
        get() = mMovie

    fun getGenreMovies(){
        job = Coroutines.ioThenMain(
                {repository.getGenreMovies()},
                { onResponseGenre(it)})
    }

    fun getMovieDetail(){
        if(mMovie.value!=null){
            job = Coroutines.ioThenMain(
                    {repository.getMovieDetail(mMovie.value!!.id)},
                    { setMovie(it) })
        }
    }

    private fun onResponseGenre(it: ResponseGenre?) {
        mGenreMovies.value = it?.genres
        getGenreName()
    }

    fun setMovie(movie: Movie?){
        mMovie.value = movie
    }

    private fun getGenreName() {
        if (mMovie.value?.genre_ids != null && mGenreMovies.value != null) {
            for(genre in mGenreMovies.value!!){
                for (g in mMovie.value?.genre_ids!!) {
                    if(genre.id==g){
                        genreValues.add(genre.name)
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}
