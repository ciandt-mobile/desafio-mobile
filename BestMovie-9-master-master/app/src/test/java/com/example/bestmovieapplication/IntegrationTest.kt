package com.example.bestmovieapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.example.bestmovieapplication.api.repository.model.Movie
import com.example.bestmovieapplication.api.repository.model.MovieApi
import com.example.bestmovieapplication.api.repository.repository.IMovieRepository
import com.example.bestmovieapplication.api.repository.repository.MovieRepository
import com.example.bestmovieapplication.feature.movies.MovieViewModel
import com.example.bestmovieapplication.feature.movies.PopularMoviesActivity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


class IntegrationTest {
    @Rule @JvmField  var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup(){

    }

    @Test
    fun fetchInvalidMovie() {

        val movieViewModel = MovieViewModel()
        movieViewModel.fetchMovie(-1)
        val testResult =  movieViewModel.specificMovieLiveData.value
        assertEquals(testResult, null)
    }

    @Test
    fun fetchValidMovie() {

        val movieViewModel = MovieViewModel()
        movieViewModel.fetchMovie(475557)

        Thread.sleep(3000)
        movieViewModel.specificMovieLiveData.observeOnce {
            assertEquals(it.title, "Joker")
        }
    }

    @Test
    fun fetchInvalidMoviesList() {

        val movieViewModel = MovieViewModel()
        movieViewModel.fetchMovies()

        movieViewModel.specificMovieLiveData.observeOnce {
        }

        assertEquals(movieViewModel.specificMovieLiveData.value, null)
    }


    @Test
    fun fetchValidMoviesList() {

        val movieViewModel = MovieViewModel()
        movieViewModel.fetchMovies()

        Thread.sleep(3000)

        movieViewModel.popularMoviesLiveData.observeOnce {
            assertEquals(it[0].title, "The Old Man & the Gun")
        }


    }

    fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
        val observer = OneTimeObserver(handler = onChangeHandler)
        observe(observer, observer)
    }
}



