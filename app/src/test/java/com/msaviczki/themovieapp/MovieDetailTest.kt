package com.msaviczki.themovieapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.msaviczki.themovieapp.data.MovieDetailMap
import com.msaviczki.themovieapp.helper.adapter.CoroutineBaseContext
import com.msaviczki.themovieapp.helper.adapter.CoroutineBaseContextImpl
import com.msaviczki.themovieapp.presentation.detail.viewmodel.MovieDetailViewModel
import com.msaviczki.themovieapp.presentation.detail.viewmodel.MovieDetailViewModelRepositoryImpl
import com.msaviczki.themovieapp.presentation.detail.viewstate.MovieDetailViewState
import io.mockk.MockKAnnotations
import com.msaviczki.themovieapp.network.core.Result.Success
import com.msaviczki.themovieapp.network.core.Result.Error
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieDetailTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    lateinit var movieDetailViewModel: MovieDetailViewModel

    @MockK
    lateinit var map : MovieDetailMap

    @MockK
    lateinit var movieRepository : MovieDetailViewModelRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        val dispatcher: CoroutineBaseContext = CoroutineBaseContextImpl()
        movieDetailViewModel = MovieDetailViewModel(movieRepository, dispatcher)
    }

    @Test
    fun `success on movie detail request`() {
        val result = MovieDetailViewState("", false, map)

        coEvery { movieRepository.getMovieById(123) } returns(Success(map))

        movieDetailViewModel.getMovieDetail(123)

        movieDetailViewModel.getMovieLiveData().observeForever {
            assertEquals(result, it)
        }
    }

    @Test
    fun `error on movie detail request`() {
        val error = "Error"
        val result = MovieDetailViewState(error, false, null)

        coEvery { movieRepository.getMovieById(123) } returns(Error(error))

        movieDetailViewModel.getMovieDetail(123)

        movieDetailViewModel.getMovieLiveData().observeForever {
            assertEquals(result, it)
        }
    }

}