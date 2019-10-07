package com.tartagliaeg.tmdb.domain.catalog.listing

import androidx.fragment.app.FragmentActivity
import com.tartagliaeg.tmdb.R
import com.tartagliaeg.tmdb.domain.catalog.CatalogModuleContract
import com.tartagliaeg.tmdb.domain.catalog.CatalogRepositoryContract
import com.tartagliaeg.tmdb.domain.catalog.details.CatalogDetailsActivity
import com.tartagliaeg.tmdb.tools_android.DimensionTools
import com.tartagliaeg.tmdb.tools_android.ImageTools
import com.tartagliaeg.tmdb.tools_android.Navigation
import com.tartagliaeg.tmdb.tools.SimpleMemoryCache


class CatalogListingModule(catalogModule: CatalogModuleContract, fragmentActivity: FragmentActivity): CatalogListingModuleContract {
    override val imageTools: ImageTools = catalogModule.imageTools
    override val dimensionTools: DimensionTools = catalogModule.dimensionTools
    override val activity: FragmentActivity = fragmentActivity
    override val repository: CatalogRepositoryContract = catalogModule.repository
    override val presenter: CatalogListingPresenterContract = CatalogListingPresenter()
    override val view: CatalogListingViewContract = activity.findViewById<CatalogListingView>(R.id.dmcatalog_cl_catalog_listing)
    override val detailsNavigation: Navigation<Int> = CatalogDetailsActivity.navigation
    override val cache: SimpleMemoryCache = SimpleMemoryCache(fragmentActivity.javaClass.name)

    init {
        presenter.module = this
        view.module = this
    }
}

