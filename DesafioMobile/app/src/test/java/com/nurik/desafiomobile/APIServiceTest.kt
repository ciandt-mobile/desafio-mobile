package com.nurik.desafiomobile

import com.nurik.desafiomobile.data.MoviesApi
import com.nurik.desafiomobile.data.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class APIServiceTest {
    private lateinit var mockWebServer : MockWebServer
    private lateinit var apiService: MoviesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        apiService = MoviesApi()
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `check api is calling with success PopularMovies`() = runBlocking<Unit> {
        val repository =  MoviesRepository(MoviesApi())
        launch(Dispatchers.Main) {
            var response = repository.getPopularMovies()
            assertEquals("is returning results", true, response.results.isNotEmpty())
        }
    }

    @Test
    fun `check api is calling with success UpcomingMovies`() = runBlocking<Unit> {
        val repository =  MoviesRepository(MoviesApi())
        launch(Dispatchers.Main) {
            var response = repository.getUpcomingMovies()
            assertEquals("is returning results", true, response.results.isNotEmpty())
        }
    }
}