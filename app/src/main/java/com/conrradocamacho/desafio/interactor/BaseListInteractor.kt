package com.conrradocamacho.desafio.interactor

import android.util.Log
import com.conrradocamacho.desafio.contract.BaseListContract
import com.conrradocamacho.desafio.network.bean.Discover
import com.conrradocamacho.desafio.network.service.AppAPI
import com.conrradocamacho.desafio.network.service.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Conrrado Camacho on 03/09/2019.
 * con.webmaster@gmail.com
 */
class BaseListInteractor(private val output: BaseListContract.InteactorOutput?):
    BaseListContract.Interactor {

    private val TAG = "BaseListInteractor"
    private val api: AppAPI = Service.getRetrofit().create(AppAPI::class.java)

    override fun getMovies() {
        val call: Call<Discover>? = api.getMovies()
        call?.enqueue(object : Callback<Discover> {
            override fun onFailure(call: Call<Discover>, t: Throwable) {
                Log.d(TAG, t.message ?: "Unknown error")
                output?.serverError()
            }

            override fun onResponse(call: Call<Discover>, response: Response<Discover>) {
                val discover = response.body()
                if (discover?.results != null) {
                    output?.getMoviesSuccess(discover.results)
                    return
                }
                output?.getMoviesError()
            }

        })
    }
}