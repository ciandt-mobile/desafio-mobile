package com.tartagliaeg.tmdb.domain.catalog

import androidx.fragment.app.FragmentActivity
import com.tartagliaeg.tmdb.settings.ENV
import com.tartagliaeg.tmdb.tools_android.DimensionTools
import com.tartagliaeg.tmdb.tools_android.ImageTools
import com.tartagliaeg.tmdb.tools.OkHttpClientTools
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface CatalogModuleContract {
    val repository: CatalogRepositoryContract
    val imageTools: ImageTools
    val dimensionTools: DimensionTools
}

class CatalogModule(activity: FragmentActivity): CatalogModuleContract {
    companion object {
        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(ENV.TMDB_API_REST_URL)
                .client(OkHttpClientTools.createClientWithDefaultQueryParameters(mapOf(Pair(ENV.TMDB_API_REST_AUTH_KEY, ENV.TMDB_API_REST_AUTH_VAL))))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        val catalogAPI: CatalogAPI by lazy { retrofit.create(CatalogAPI::class.java) }
    }

    override val repository: CatalogRepositoryContract
    override val imageTools: ImageTools
    override val dimensionTools: DimensionTools

    init {
        this.repository = CatalogRepository(catalogAPI)
        this.imageTools = ImageTools(activity)
        this.dimensionTools = DimensionTools(activity)
    }

}