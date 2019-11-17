package br.com.victoroliveira.cit

import br.com.victoroliveira.cit.data.model.*
import br.com.victoroliveira.cit.data.repository.MovieRepositoryImp
import br.com.victoroliveira.cit.domain.MovieRepository
import org.mockito.Mockito

open class BaseUnitTest {

    var repoMock: MovieRepository = Mockito.mock(MovieRepositoryImp::class.java)

    fun createUpcoming(): UpcomingResponse {
        return UpcomingResponse(
            listOf(
                Movie(
                    195.531,
                    30,
                    false,
                    "/h6Wi81XNXCjTAcdstiCLRykN3Pa.jpg",
                    330457,
                    false,
                    "/xJWPZIYOEFIjZpBL7SVBGnzRYXp.jpg",
                    "en",
                    "Frozen II",
                    listOf(30, 12),
                    "Frozen II",
                    5.4,
                    "Elsa, Anna, Kristoff and Olaf are going far in the forest to know the truth about an ancient mystery of their kingdom.",
                    "2019-11-20"
                )
            ), 1, 1, Dates("2019-12-08", "2019-11-23"), 1
        )
    }

    fun createPopular(): PopularResponse {
        return PopularResponse(
            1, 1, 1, listOf(
                Movie(
                    195.531,
                    30,
                    false,
                    "/h6Wi81XNXCjTAcdstiCLRykN3Pa.jpg",
                    330457,
                    false,
                    "/xJWPZIYOEFIjZpBL7SVBGnzRYXp.jpg",
                    "en",
                    "Frozen II",
                    listOf(30, 12),
                    "Frozen II",
                    5.4,
                    "Elsa, Anna, Kristoff and Olaf are going far in the forest to know the truth about an ancient mystery of their kingdom.",
                    "2019-11-20"
                )
            )
        )
    }

    fun createCast(): CastResponse {
        return CastResponse(
            475557,
            listOf(
                Cast(
                    2,
                    "Arthur Fleck / Joker",
                    "5a88f80a9251410b4d05826b",
                    2,
                    73421,
                    "Joaquin Phoenix",
                    0,
                    "/zixTWuMZ1D8EopgOhLVZ6Js2ux3.jpg"
                )
            ),
            listOf(
                Crew(
                    "5c6dd8aa0e0a262c99a1aed3",
                    "Writing",
                    2,
                    324,
                    "Writer",
                    "Scott Silver",
                    "/vRblvymfxaMd7Lsqs4ypVAfnJK.jp"
                )
            )
        )
    }

    fun createDetail(): DetailResponse {
        return DetailResponse(
            false,
            "/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg",
            BelongsToCollection(0, "", "", ""),
            55000000,
            listOf(Genres(80, "Crime")),
            "http://www.jokermovie.net",
            475557,
            "tt7286456",
            "en",
            "Joker",
            "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
            404.293,
            "/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
            listOf(
                ProductionCompanies(
                    9993,
                    "/2Tc1P3Ac8M479naPp1kYT3izLS5.png",
                    "DC Entertainment",
                    "US"
                )
            ),
            listOf(ProductionCountries("US", "United States of America")),
            "2019-10-02",
            995064593,
            122,
            listOf(SpokenLanguages("en", "English")),
            "Released",
            "Put on a happy face.",
            "Joker",
            false,
            8.4,
            5503
        )
    }
}