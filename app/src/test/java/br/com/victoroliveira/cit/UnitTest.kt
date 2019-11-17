package br.com.victoroliveira.cit

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.`when`

class UnitTest : BaseUnitTest() {

    @Test
    fun testUpcomingList() {
        runBlocking {
            val responseMock = createUpcoming()
            `when`(repoMock.getUpcoming(1, "pt-BR")).thenReturn(responseMock)
            assertEquals(
                responseMock.results.size,
                repoMock.getUpcoming(1, "pt-BR").results.size
            )
        }
    }

    @Test
    fun testUpcomingListNotNull() {
        runBlocking {
            val responseMock = createUpcoming()
            `when`(repoMock.getUpcoming(1, "pt-BR")).thenReturn(responseMock)
            responseMock.results.forEach {
                assertNotNull(it)
            }
        }
    }

    @Test
    fun testPopularList() {
        runBlocking {
            val responseMock = createPopular()
            `when`(repoMock.getPopular(1, "pt-BR")).thenReturn(responseMock)
            assertEquals(
                responseMock.results.size,
                repoMock.getPopular(1, "pt-BR").results.size
            )
        }
    }

    @Test
    fun testPopularListNotNull() {
        runBlocking {
            val responseMock = createPopular()
            `when`(repoMock.getPopular(1, "pt-BR")).thenReturn(responseMock)
            responseMock.results.forEach {
                assertNotNull(it)
            }
        }
    }

    @Test
    fun testCastList() {
        runBlocking {
            val responseMock = createCast()
            `when`(repoMock.getCast(475557)).thenReturn(responseMock)
            assertEquals(
                responseMock.cast.size,
                repoMock.getCast(475557).cast.size
            )
        }
    }

    @Test
    fun testCastListNotNull() {
        runBlocking {
            val responseMock = createCast()
            `when`(repoMock.getCast(475557)).thenReturn(responseMock)
            responseMock.cast.forEach {
                assertNotNull(it)
            }
        }
    }

    @Test
    fun testDetailList() {
        runBlocking {
            val responseMock = createDetail()
            `when`(repoMock.getDetail(475557)).thenReturn(responseMock)
            assertEquals(
                responseMock.genres.size,
                repoMock.getCast(475557).cast.size
            )
        }
    }

    @Test
    fun testDetailListNotNull() {
        runBlocking {
            val responseMock = createDetail()
            `when`(repoMock.getDetail(475557)).thenReturn(responseMock)
            responseMock.genres.forEach {
                assertNotNull(it)
            }
        }
    }
}