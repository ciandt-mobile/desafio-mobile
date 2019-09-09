package com.conrradocamacho.desafio.contract

import com.conrradocamacho.desafio.network.bean.Credit
import com.conrradocamacho.desafio.network.bean.Detail

/**
 * Created by Conrrado Camacho on 08/09/2019.
 * con.webmaster@gmail.com
 */
interface DetailContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun updateScreenWithDetails(detail: Detail)
        fun updateScreenWithCredits(credit: Credit)
        fun messageError(message: String)
    }

    interface Presenter {
        fun onGetDetails(movieId: Int)
        fun onGetCredits(movieId: Int)
    }

    interface Interactor {
        fun getDetails(movieId: Int)
        fun getCredits(movieId: Int)
    }

    interface InteractorOutput {
        fun getDetailsSuccess(detail: Detail)
        fun getCreditSuccess(credit: Credit)
        fun getError()
        fun serverError()
    }
}