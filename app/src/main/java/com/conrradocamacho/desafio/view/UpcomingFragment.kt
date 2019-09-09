package com.conrradocamacho.desafio.view

import android.util.Log
import com.conrradocamacho.desafio.network.bean.Movie
import com.conrradocamacho.desafio.utils.Util
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Conrrado Camacho on 09/09/2019.
 * con.webmaster@gmail.com
 */
class UpcomingFragment: BaseListFragment() {
    companion object {
        private const val tag = "UpcomingFragment"
    }


    override fun updateList(movieList: List<Movie>) {

        val list = mutableListOf<Movie>()
        try {
            for (movie in movieList) {
                val format = SimpleDateFormat(Util.formatDateServer, Locale.US)

                val yearMovie = Util.getYearFromDateServer(movie.releaseDate)

                val stringDateNow = format.format(Date())
                val yearNow = Util.getYearFromDateServer(stringDateNow)

                if (yearMovie.toInt() >= yearNow.toInt()) {
                    list.add(movie)
                }
            }
        }
        catch (e: Exception) {
            Log.d(tag, e.message ?: "")
        }

        this.adapter.updateList(list)
    }
}