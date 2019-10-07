package com.tartagliaeg.tmdb.domain.catalog.listing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tartagliaeg.tmdb.R
import com.tartagliaeg.tmdb.domain.catalog.CatalogModule

class CatalogListingActivity : AppCompatActivity() {
    private lateinit var catalogListingModule: CatalogListingModuleContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dmcatalog_activity_catalog_listing)

        catalogListingModule = CatalogListingModule(CatalogModule(this), this)
    }

    override fun onStart() {
        super.onStart()
        catalogListingModule.view.start()
    }

    override fun onStop() {
        super.onStop()
        catalogListingModule.view.stop()
    }
}
