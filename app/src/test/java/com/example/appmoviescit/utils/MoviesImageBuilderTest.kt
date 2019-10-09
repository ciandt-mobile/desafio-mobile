package com.example.appmoviescit.utils

import com.movies.appmoviescit.services.MoviesConstants.Companion.API_KEY
import com.movies.appmoviescit.utils.MoviesImageBuilder
import org.junit.Assert.assertEquals
import org.junit.Test

private val EXPECTED_POSTER_URL = "https://image.tmdb.org/t/p/w154/poster?api_key=$API_KEY"
private val EXPECTED_BACKDROP_URL = "https://image.tmdb.org/t/p/w780/backdrop?api_key=$API_KEY"

class MovieImageUrlBuilderTest {

    @Test
    fun testUrlBuilder() {
        val moviesImageBuilder = MoviesImageBuilder()
        val posterBuiltURL = moviesImageBuilder.buildPosterUrl("/poster")
        val backdropBuiltURL = moviesImageBuilder.buildBackdropUrl("/backdrop")

        assertEquals(EXPECTED_POSTER_URL, posterBuiltURL)
        assertEquals(EXPECTED_BACKDROP_URL, backdropBuiltURL)
    }


}