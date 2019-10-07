package com.tartagliaeg.tmdb.domain.catalog.listing

import androidx.fragment.app.FragmentActivity
import com.tartagliaeg.tmdb.domain.catalog.CatalogRepositoryContract
import com.tartagliaeg.tmdb.domain.catalog.Movie
import com.tartagliaeg.tmdb.domain.catalog.MovieImages
import com.tartagliaeg.tmdb.domain.catalog.TMDBPage
import com.tartagliaeg.tmdb.tools_android.DimensionTools
import com.tartagliaeg.tmdb.tools_android.ImageTools
import com.tartagliaeg.tmdb.tools_android.Navigation
import com.tartagliaeg.tmdb.tools.SimpleMemoryCache

interface CatalogListingViewContract {
    var module: CatalogListingModuleContract

    fun start()
    fun stop()

    fun showCatalogsContent(state: CatalogListingViewState)
    fun showCatalogsLoading()
    fun showCatalogsFailure(message: String)
}

object CatalogListingConstants {
    val ORDER_BY_DATE = 1
    val ORDER_BY_POPULARITY = 2
}


interface CatalogListingPresenterContract {
    var module: CatalogListingModuleContract

    fun didRequestLoadCatalog(page: Int, images: MovieImages, orderedBy: Int)
    fun didRequestLoadCancellation()
    fun didTouchOnCatalogItem(movie: Movie)
}

interface CatalogListingModuleContract {
    val imageTools: ImageTools
    val dimensionTools: DimensionTools
    val repository: CatalogRepositoryContract
    val presenter: CatalogListingPresenterContract
    val view: CatalogListingViewContract
    val activity: FragmentActivity
    val detailsNavigation: Navigation<Int>
    val cache: SimpleMemoryCache
}

class CatalogListingViewState(
    val catalog: TMDBPage<Movie>,
    val order: Int
)
