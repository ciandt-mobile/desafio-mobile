package com.tartagliaeg.tmdb.domain.catalog.details

import androidx.fragment.app.FragmentActivity
import com.tartagliaeg.tmdb.domain.catalog.CatalogRepositoryContract
import com.tartagliaeg.tmdb.domain.catalog.Movie
import com.tartagliaeg.tmdb.domain.catalog.MovieImages
import com.tartagliaeg.tmdb.tools_android.DimensionTools
import com.tartagliaeg.tmdb.tools_android.ImageTools

interface CatalogDetailsViewContract {
    var module: CatalogDetailsModuleContract

    fun start(id: Int)
    fun stop()

    fun showDetailsContent(movie: Movie)
    fun showDetailsLoading()
    fun showDetailsFailure(message: String)
}

interface CatalogDetailsPresenterContract {
    var module: CatalogDetailsModuleContract

    fun didRequestLoadDetails(id: Int, images: MovieImages)
    fun didRequestLoadCancellation()
}

interface CatalogDetailsModuleContract {
    val imageTools: ImageTools
    val dimensionTools: DimensionTools
    val repository: CatalogRepositoryContract
    val presenter: CatalogDetailsPresenterContract
    val view: CatalogDetailsViewContract
    val activity: FragmentActivity
}
