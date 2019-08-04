package br.com.architerure.movies.app.viewModel

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class MoviesViewModelTest {

    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var application: Application
    private lateinit var context: Context


    @Before
    fun setUp() {
        application = Mockito.mock(Application::class.java)
        moviesViewModel = MoviesViewModel(application)
        context = Mockito.mock(Context::class.java)
    }

    @Test
    fun popularMovies() {
        moviesViewModel.popularMovies()

        Handler(Looper.getMainLooper()).post {
            moviesViewModel.allMovies.observeForever {
                assertNotNull(it.movies)
            }
        }
    }

    @Test
    fun upComingMovies() {
        moviesViewModel.upComingMovies()

        Handler(Looper.getMainLooper()).post {
            moviesViewModel.allMovies.observeForever {
                assertNotNull(it.movies)
            }
        }
    }

    @Test
    fun genreList() {
        moviesViewModel.genreList()

        Handler(Looper.getMainLooper()).post {
            moviesViewModel.allGenres.observeForever {
                assertNotNull(it.genres)
            }
        }
    }

    @Test
    fun castList() {
        moviesViewModel.popularMovies()

        Handler(Looper.getMainLooper()).post {
            moviesViewModel.allCast.observeForever {
                assertNotNull(it.cast)
            }
        }
    }
}