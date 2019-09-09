package com.conrradocamacho.desafio.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.conrradocamacho.desafio.BuildConfig
import com.conrradocamacho.desafio.R
import com.conrradocamacho.desafio.contract.DetailContract
import com.conrradocamacho.desafio.network.bean.Credit
import com.conrradocamacho.desafio.network.bean.Detail
import com.conrradocamacho.desafio.presenter.DetailPresenter
import com.conrradocamacho.desafio.utils.Util
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by Conrrado Camacho on 08/09/2019.
 * con.webmaster@gmail.com
 */
class DetailActivity: AppCompatActivity(), DetailContract.View {
    companion object {
        const val extraMovieId = "extraMovieId"
    }

    private lateinit var presenter: DetailContract.Presenter
    private var movieId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
//        setSupportActionBar(toolbar)
//        title = ""

        movieId = intent.getIntExtra(extraMovieId, 0)

        presenter = DetailPresenter(this, this)
        presenter.onGetDetails(movieId)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun updateScreenWithDetails(detail: Detail) {
        Glide.with(this)
            .load("${BuildConfig.BASE_IMAGE_URL}t/p/w300/${detail.posterPath}")
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
        Log.d("TESTE", credit.toString())
    }

    override fun messageError(message: String) {
    }


}