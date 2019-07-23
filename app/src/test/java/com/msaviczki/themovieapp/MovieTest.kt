package com.msaviczki.themovieapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.msaviczki.themovieapp.data.MovieMap
import com.msaviczki.themovieapp.helper.adapter.CoroutineBaseContext
import com.msaviczki.themovieapp.helper.adapter.CoroutineBaseContextImpl
import com.msaviczki.themovieapp.network.core.Result
import com.msaviczki.themovieapp.presentation.movie.viewmodel.MovieViewModel
import com.msaviczki.themovieapp.presentation.movie.viewmodel.MovieViewModelRepositoryImpl
import com.msaviczki.themovieapp.presentation.movie.viewstate.MovieViewState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    lateinit var movieViewModel: MovieViewModel

    @MockK
    lateinit var list : MutableList<MovieMap>

    @MockK
    lateinit var movieRepository : MovieViewModelRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        val dispatcher: CoroutineBaseContext = CoroutineBaseContextImpl()
        movieViewModel = MovieViewModel(movieRepository, dispatcher)
    }

    @Test
    fun `success on popular movies request`() {
        val result = MovieViewState("", false, list)

        coEvery { movieRepository.requestPopularMovies() } returns(Result.Success(list))

        movieViewModel.requestPopularMovies()

        movieViewModel.getMovieLiveData().observeForever {
            assertEquals(result, it)
        }
    }

    @Test
    fun `success on upcoming movies request`() {
        val result = MovieViewState("", false, list)

        coEvery { movieRepository.requestUpcomingMovies() } returns(Result.Success(list))

        movieViewModel.requestUpComingMovies()

        movieViewModel.getMovieLiveData().observeForever {
            assertEquals(result, it)
        }
    }

    @Test
    fun `error on popular movies request`() {
        val error = "Error"
        val result = MovieViewState(error, false)

        coEvery { movieRepository.requestPopularMovies() } returns(Result.Error(error))

        movieViewModel.requestPopularMovies()

        movieViewModel.getMovieLiveData().observeForever {
            assertEquals(result, it)
        }
    }

    @Test
    fun `error on upcoming movies request`() {
        val error = "Error"
        val result = MovieViewState(error, false)

        coEvery { movieRepository.requestUpcomingMovies() } returns(Result.Error(error))

        movieViewModel.requestUpComingMovies()

        movieViewModel.getMovieLiveData().observeForever {
            assertEquals(result, it)
        }
    }
}

