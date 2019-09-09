package com.conrradocamacho.desafio.view

import com.conrradocamacho.desafio.network.bean.Movie

/**
 * Created by Conrrado Camacho on 09/09/2019.
 * con.webmaster@gmail.com
 */
class PopularFragment: BaseListFragment() {

    override fun updateList(movieList: List<Movie>) {
        this.adapter.updateList(movieList)
    }
}