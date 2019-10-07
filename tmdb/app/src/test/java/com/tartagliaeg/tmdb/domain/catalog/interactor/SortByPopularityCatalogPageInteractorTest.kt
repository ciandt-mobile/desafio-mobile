package com.tartagliaeg.tmdb.domain.catalog.interactor

import com.tartagliaeg.tmdb.domain.catalog.Movie
import com.tartagliaeg.tmdb.domain.catalog.TMDBPage
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Test


class SortByPopularityCatalogPageInteractorTest {
    @Test
    fun catalogPage_isSortedByPopularity() {
        val to = TestObserver.create<TMDBPage<Movie>>()

        Single.just(TMDBPage(
            page = 1,
            results = listOf(
                Movie(title = "10", popularity = 10f),
                Movie(title = "100", popularity = 100f),
                Movie(title = "80", popularity = 80f),
                Movie(title = "30", popularity = 30f),
                Movie(title = "50", popularity = 50f)
            )
        ))
            .compose(sortByPopularityCatalogPage())
            .subscribe(to)

        to.assertNoErrors()
        assertEquals(to.values().size, 1)

        assertEquals(to.values()[0].results[0].title, "100")
        assertEquals(to.values()[0].results[1].title, "80")
        assertEquals(to.values()[0].results[2].title, "50")
        assertEquals(to.values()[0].results[3].title, "30")
        assertEquals(to.values()[0].results[4].title, "10")
    }

    @Test
    fun catalogPage_doesNotModifyTheParameter() {
        val to = TestObserver.create<TMDBPage<Movie>>()
        val originalPage = TMDBPage(
            page = 1,
            results = listOf(
                Movie(title = "10", popularity = 10f),
                Movie(title = "100", popularity = 100f),
                Movie(title = "80", popularity = 80f)
            )
        )

        Single.just(originalPage)
            .compose(sortByPopularityCatalogPage())
            .subscribe(to)

        assertEquals(originalPage.results[0].title, "10")
        assertEquals(originalPage.results[1].title, "100")
        assertEquals(originalPage.results[2].title, "80")
    }
}
