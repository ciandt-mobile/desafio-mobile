package com.conrradocamacho.desafio.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.conrradocamacho.desafio.BuildConfig
import com.conrradocamacho.desafio.R
import com.conrradocamacho.desafio.contract.DetailContract
import com.conrradocamacho.desafio.network.bean.Credit
import com.conrradocamacho.desafio.network.bean.Detail
import com.conrradocamacho.desafio.presenter.DetailPresenter
import com.conrradocamacho.desafio.utils.Util
import com.conrradocamacho.desafio.view.adapter.CreditListAdapter
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by Conrrado Camacho on 08/09/2019.
 * con.webmaster@gmail.com
 */
class DetailActivity: AppCompatActivity(), DetailContract.View {
    companion object {
        const val extraMovieId = "extraMovieId"
    }

    private lateinit var adapter: CreditListAdapter
    private lateinit var presenter: DetailContract.Presenter
    private var movieId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        movieId = intent.getIntExtra(extraMovieId, 0)

        adapter = CreditListAdapter(mutableListOf())
        initRecyclerView()

        presenter = DetailPresenter(this, this)
        presenter.onGetDetails(movieId)
    }

    private fun initRecyclerView() {
        val gridLayoutManager = GridLayoutManager(this, GridLayoutManager.VERTICAL)

        // todo fazer a verificacao se está portrait ou landscape
        gridLayoutManager.spanCount = 3

        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun updateScreenWithDetails(detail: Detail) {
        Glide.with(this)
            .load("${BuildConfig.BASE_IMAGE_URL}t/p/w300/${detail.backdropPath}")
            .into(imageView)
        movieTitle.text = detail.title

        val year = Util.getYearFromDateServer(detail.releaseDate)
        val genres = Util.getTextFromItemList(detail.genres ?: arrayListOf())
        val text = "$year - ${detail.runtime}min - $genres"

        aditionalInformation.text = text
        overview.text = detail.overview

        presenter.onGetCredits(movieId)
    }

    override fun updateScreenWithCredits(credit: Credit) {
        adapter.updateList(credit.cast ?: mutableListOf())
    }

    override fun messageError(message: String) {
    }


}