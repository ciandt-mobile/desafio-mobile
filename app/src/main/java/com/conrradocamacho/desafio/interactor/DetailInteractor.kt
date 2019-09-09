package com.conrradocamacho.desafio.interactor

import android.util.Log
import com.conrradocamacho.desafio.contract.DetailContract
import com.conrradocamacho.desafio.network.bean.Credit
import com.conrradocamacho.desafio.network.bean.Detail
import com.conrradocamacho.desafio.network.service.AppAPI
import com.conrradocamacho.desafio.network.service.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Conrrado Camacho on 08/09/2019.
 * con.webmaster@gmail.com
 */
class DetailInteractor(private val output: DetailContract.InteractorOutput?):
    DetailContract.Interactor {

    private val tag = "DetailInteractor"
    private val api: AppAPI = Service.getRetrofit().create(AppAPI::class.java)

    override fun getDetails(movieId: Int) {
        val call: Call<Detail>? = api.getDetails(movieId)
        call?.enqueue(object : Callback<Detail> {
            override fun onFailure(call: Call<Detail>, t: Throwable) {
                Log.d(tag, t.message ?: "Unknows error")
                output?.serverError()
            }

            override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                val detail = response.body()
                if (detail is Detail) {
                    output?.getDetailsSuccess(detail)
                    return
                }
                output?.getError()
            }

        })
    }

    override fun getCredits(movieId: Int) {
        val call: Call<Credit>? = api.getCredits(movieId)
        call?.enqueue(object : Callback<Credit> {
            override fun onFailure(call: Call<Credit>, t: Throwable) {
                Log.d(tag, t.message ?: "Unknows error")
                output?.serverError()
            }

            override fun onResponse(call: Call<Credit>, response: Response<Credit>) {
                val credit = response.body()
                if (credit is Credit) {
                    output?.getCreditSuccess(credit)
                    return
                }
                output?.getError()
            }

        })
    }
}