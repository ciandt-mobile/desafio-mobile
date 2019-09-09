package com.conrradocamacho.desafio.router

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.conrradocamacho.desafio.contract.BaseListContract
import com.conrradocamacho.desafio.network.bean.Movie
import com.conrradocamacho.desafio.view.DetailActivity

/**
 * Created by Conrrado Camacho on 03/09/2019.
 * con.webmaster@gmail.com
 */
class BaseListRouter(private val fragmentActivity: FragmentActivity?): BaseListContract.Router {

    override fun showDetail(movie: Movie) {
        val intent = Intent(fragmentActivity?.applicationContext, DetailActivity::class.java)
        intent.putExtra(DetailActivity.extraMovieId, movie.id)
        fragmentActivity?.startActivity(intent)
    }
}