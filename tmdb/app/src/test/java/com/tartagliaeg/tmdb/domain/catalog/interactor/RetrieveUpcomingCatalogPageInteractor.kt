package com.tartagliaeg.tmdb.domain.catalog.interactor

import com.tartagliaeg.tmdb.domain.catalog.CatalogRepositoryContract
import com.tartagliaeg.tmdb.domain.catalog.Movie
import com.tartagliaeg.tmdb.domain.catalog.TMDBPage
import com.tartagliaeg.tmdb.tools.SimpleMemoryCache
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import com.nhaarman.mockitokotlin2.anyOrNull


class RetrieveUpcomingCatalogPageInteractorTest {
    @Test
    fun shouldFetchDataFromRepositoryOnly() {
        var to = TestObserver.create<TMDBPage<Movie>>()

        val repo = Mockito.mock(CatalogRepositoryContract::class.java)
        Mockito.`when`(repo.getUpcomingMovies(1)).thenReturn(Single.just(TMDBPage(
            page = 1,
            results = listOf(
                Movie(title = "10", popularity = 10f),
                Movie(title = "100", popularity = 100f),
                Movie(title = "80", popularity = 80f),
                Movie(title = "30", popularity = 30f),
                Movie(title = "50", popularity = 50f)
            )
        )))

        Single.just(repo)
            .compose(retrieveUpcomingCatalogPage(1))
            .subscribe(to)

        to.assertNoErrors()
        Assert.assertEquals(to.values()[0].results.size, 5)
        Mockito.verify(repo, Mockito.only()).getUpcomingMovies(1)

        to =  TestObserver.create<TMDBPage<Movie>>()
        Single.just(repo)
            .compose(retrieveUpcomingCatalogPage(1))
            .subscribe(to)

        to.assertNoErrors()
        Assert.assertEquals(to.values()[0].results.size, 5)
        Mockito.verify(repo, Mockito.times(2)).getUpcomingMovies(1)

    }
}


class RetrieveCachedUpcomingCatalogPageInteractorTest {
    @Test
    fun shouldFetchDataFromCacheAndRepository() {
        var to = TestObserver.create<TMDBPage<Movie>>()
        val cache = Mockito.spy(SimpleMemoryCache("ANY"))

        val repo = Mockito.mock(CatalogRepositoryContract::class.java)
        Mockito.`when`(repo.getUpcomingMovies(1)).thenReturn(Single.just(TMDBPage(
            page = 1,
            results = listOf(
                Movie(title = "10", popularity = 10f),
                Movie(title = "100", popularity = 100f),
                Movie(title = "80", popularity = 80f),
                Movie(title = "30", popularity = 30f),
                Movie(title = "50", popularity = 50f)
            )
        )))

        Single.just(repo)
            .compose(retrieveCachedUpcomingCatalogPage(1, cache))
            .subscribe(to)

        to.assertNoErrors()
        Assert.assertEquals(to.values()[0].results.size, 5)

        Mockito.verify(repo, Mockito.times(1)).getUpcomingMovies(1)
        Mockito.verify(cache, Mockito.times(1)).has(Mockito.anyString())
        Mockito.verify(cache, Mockito.never()).get<Movie>(Mockito.anyString())
        Mockito.verify(cache, Mockito.times(1)).set(Mockito.anyString(), anyOrNull())


        Mockito.reset(repo)
        Mockito.reset(cache)

        to =  TestObserver.create<TMDBPage<Movie>>()
        Single.just(repo)
            .compose(retrieveCachedUpcomingCatalogPage(1, cache))
            .subscribe(to)

        to.assertNoErrors()
        Mockito.verify(repo, Mockito.times(0)).getUpcomingMovies(1) // Should not call the repo since the page is cached
        Mockito.verify(cache, Mockito.times(1)).has(Mockito.anyString())
        Mockito.verify(cache, Mockito.times(1)).get<Movie>(Mockito.anyString())
        Mockito.verify(cache, Mockito.times(1)).set(Mockito.anyString(), anyOrNull())
    }
}