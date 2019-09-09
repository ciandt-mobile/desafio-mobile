package com.conrradocamacho.desafio.presenter

import android.app.Activity
import com.conrradocamacho.desafio.R
import com.conrradocamacho.desafio.contract.DetailContract
import com.conrradocamacho.desafio.interactor.DetailInteractor
import com.conrradocamacho.desafio.network.bean.Credit
import com.conrradocamacho.desafio.network.bean.Detail

/**
 * Created by Conrrado Camacho on 08/09/2019.
 * con.webmaster@gmail.com
 */
class DetailPresenter(private val view: DetailContract.View?, private val activity: Activity): DetailContract.Presenter,
    DetailContract.InteractorOutput {

    private val interactor: DetailContract.Interactor = DetailInteractor(this)

    override fun onGetDetails(movieId: Int) {
        view?.showLoading()
        interactor.getDetails(movieId)
    }

    override fun onGetCredits(movieId: Int) {
        view?.showLoading()
        interactor.getCredits(movieId)
    }

    override fun getDetailsSuccess(detail: Detail) {
        view?.hideLoading()
        view?.updateScreenWithDetails(detail)
    }

    override fun getCreditSuccess(credit: Credit) {
        view?.hideLoading()
        view?.updateScreenWithCredits(credit)
    }

    override fun getError() {
        view?.hideLoading()
        view?.messageError(activity.getString(R.string.data_null))
    }

    override fun serverError() {
        view?.hideLoading()
        view?.messageError(activity.getString(R.string.server_error))
    }
}