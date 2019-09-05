package com.conrradocamacho.desafio.presenter

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.conrradocamacho.desafio.contract.BaseListContract
import com.conrradocamacho.desafio.interactor.BaseListInteractor
import com.conrradocamacho.desafio.network.bean.Movie
import com.conrradocamacho.desafio.router.BaseListRouter

/**
 * Created by Conrrado Camacho on 02/09/2019.
 * con.webmaster@gmail.com
 */
class BaseListPresenter(private val view: BaseListContract.View?,
                        private val fragmentActivity: FragmentActivity?): BaseListContract.Presenter,
    BaseListContract.InteactorOutput {

    private val interactor: BaseListContract.Interactor = BaseListInteractor(this)
    private val router: BaseListContract.Router = BaseListRouter(fragmentActivity)

    override fun onGetMovies() {
        view?.showLoading()
        interactor.getMovies()
    }

    override fun showDetail(movie: Movie) {
        router.showDetail(movie)
    }

    override fun getMoviesSuccess(movieList: List<Movie>) {
        view?.hideLoading()
        view?.updateScreen(movieList)
    }

    override fun getMoviesError() {
        view?.hideLoading()
        view?.messageError("")
    }

    override fun serverError() {
        view?.hideLoading()
        view?.messageError("")
    }
}