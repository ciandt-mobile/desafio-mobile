package com.conrradocamacho.desafio.contract

import com.conrradocamacho.desafio.network.bean.Movie

/**
 * Created by Conrrado Camacho on 03/09/2019.
 * con.webmaster@gmail.com
 */
interface BaseListContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun updateScreen(movieList: List<Movie>)
        fun messageError(message: String)
    }

    interface Presenter {
        fun onGetMovies()
        fun showDetail(movie: Movie)
    }

    interface Interactor {
        fun getMovies()
    }

    interface InteactorOutput {
        fun getMoviesSuccess(movieList: List<Movie>)
        fun getMoviesError()
        fun serverError()
    }

    interface Router {
        fun showDetail(movie: Movie)
    }

}