package com.brunodiegom.movies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.brunodiegom.movies.api.MovieRepository
import com.brunodiegom.movies.model.Cast
import com.brunodiegom.movies.model.CastResponse
import com.brunodiegom.movies.model.Detail
import com.brunodiegom.movies.model.Detail.Companion.BACKDROP_BASE_URL
import com.brunodiegom.movies.model.Genre
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations

class DetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieRepository

    @Mock
    private lateinit var observerString: Observer<String>

    @Mock
    private lateinit var observerCast: Observer<List<Cast>>

    private lateinit var viewModel: DetailViewModel

    private val detail = Detail(
        id = 1,
        backdropUrl = "/backdrop.jpg",
        title = "Title",
        duration = 190,
        genres = listOf(Genre(1, "Comedy"), Genre(2, "Action")),
        overview = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.",
        release = "2019-02-10"
    )

    private val cast = Cast(
        id = 1,
        name = "John",
        character = "Charlinho",
        picture = "cast.jpg"
    )

    private val castResponse = CastResponse(
        id = 1,
        cast = listOf(cast)
    )

    init {
        MockitoAnnotations.initMocks(this)
    }

    @Before
    fun setup() {
        viewModel = DetailViewModel(repository)
        viewModel.title.observeForever(observerString)
        viewModel.release.observeForever(observerString)
        viewModel.summary.observeForever(observerString)
        viewModel.overview.observeForever(observerString)
        viewModel.backdropUrl.observeForever(observerString)
        viewModel.cast.observeForever(observerCast)
        doReturn(Single.just(detail)).whenever(repository).getDetail(ArgumentMatchers.anyInt())
        doReturn(Single.just(castResponse)).whenever(repository).getCast(ArgumentMatchers.anyInt())
    }

    @Test
    fun `When data is requested then the title value should be returned`() {
        viewModel.start(1)
        assertThat(viewModel.title.value).isEqualTo("Title")
    }

    @Test
    fun `When data is requested then the release data should shown only the year part`() {
        viewModel.start(1)
        assertThat(viewModel.release.value).isEqualTo("2019")
    }

    @Test
    fun `When data is requested then the summary should be built`() {
        viewModel.start(1)
        assertThat(viewModel.summary.value).isEqualTo("190m | Comedy, Action")
    }

    @Test
    fun `When data is requested then overview should be returned`() {
        viewModel.start(1)
        assertThat(viewModel.overview.value).isEqualTo("Lorem ipsum dolor sit amet, consectetuer adipiscing elit.")
    }

    @Test
    fun `When data is requested then the backdrop url should be built`() {
        viewModel.start(1)
        assertThat(viewModel.backdropUrl.value).isEqualTo("$BACKDROP_BASE_URL/backdrop.jpg")
    }

    @Test
    fun `When data is requested then the list of cast should be returned`() {
        viewModel.start(1)
        assertThat(viewModel.cast.value).isEqualTo(listOf(cast))
    }
}
