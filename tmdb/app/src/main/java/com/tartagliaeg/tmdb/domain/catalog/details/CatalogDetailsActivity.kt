package com.tartagliaeg.tmdb.domain.catalog.details

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.tartagliaeg.tmdb.R
import com.tartagliaeg.tmdb.domain.catalog.CatalogModule
import com.tartagliaeg.tmdb.tools_android.Navigation

class CatalogDetailsActivity : AppCompatActivity() {
    private lateinit var catalogDetailsModule: CatalogDetailsModuleContract

    companion object {
        private val EXT_ID = "${CatalogDetailsActivity::javaClass.name}:ID"
        val navigation = object : Navigation<Int> {
            override fun navigate(activity: FragmentActivity, id: Int) {
                val intent = Intent(activity, CatalogDetailsActivity::class.java)
                intent.putExtra(EXT_ID, id)
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dmcatalog_activity_catalog_details)
        catalogDetailsModule = CatalogDetailsModule(CatalogModule(this), this)
    }

    override fun onStart() {
        super.onStart()
        catalogDetailsModule.view.start(intent.extras!!.getInt(EXT_ID))
    }

    override fun onStop() {
        super.onStop()
        catalogDetailsModule.view.stop()
    }
}
