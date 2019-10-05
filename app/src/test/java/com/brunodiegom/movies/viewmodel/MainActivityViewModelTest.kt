package com.brunodiegom.movies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.brunodiegom.movies.R
import com.brunodiegom.movies.api.MovieRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainActivityViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieRepository

    @Mock
    private lateinit var observerInt: Observer<Int>

    private lateinit var viewModel: MainActivityViewModel

    init {
        MockitoAnnotations.initMocks(this)
    }

    @Before
    fun setup() {
        viewModel = MainActivityViewModel(repository)
        viewModel.title.observeForever(observerInt)
    }

    @Test
    fun `When upcoming filter is selected then show the upcoming title`() {
        viewModel.selectUpcoming()
        Assert.assertEquals(viewModel.title.value, R.string.upcoming_movies)
    }

    @Test
    fun `When popular filter is selected then show the popular title`() {
        viewModel.selectPopular()
        Assert.assertEquals(viewModel.title.value, R.string.popular_movies)
    }
}
