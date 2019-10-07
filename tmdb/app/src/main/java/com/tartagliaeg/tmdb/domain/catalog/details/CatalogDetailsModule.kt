package com.tartagliaeg.tmdb.domain.catalog.details

import androidx.fragment.app.FragmentActivity
import com.tartagliaeg.tmdb.R
import com.tartagliaeg.tmdb.domain.catalog.CatalogModuleContract
import com.tartagliaeg.tmdb.domain.catalog.CatalogRepositoryContract
import com.tartagliaeg.tmdb.tools_android.DimensionTools
import com.tartagliaeg.tmdb.tools_android.ImageTools

class CatalogDetailsModule(
    catalogModule: CatalogModuleContract,
    fragmentActivity: FragmentActivity
) :
    CatalogDetailsModuleContract {
    override val imageTools: ImageTools = catalogModule.imageTools
    override val dimensionTools: DimensionTools = catalogModule.dimensionTools
    override val activity: FragmentActivity = fragmentActivity
    override val repository: CatalogRepositoryContract = catalogModule.repository
    override val presenter: CatalogDetailsPresenterContract = CatalogDetailsPresenter()
    override val view: CatalogDetailsViewContract =
        activity.findViewById<CatalogDetailsView>(R.id.dmcatalog_details_cv_catalog)

    init {
        presenter.module = this
        view.module = this
    }
}