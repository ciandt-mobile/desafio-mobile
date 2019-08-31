/*
 * BaseRepository.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 30/08/19 05:43
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.projectStructure


import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

abstract class BaseRepository {

    protected inline fun <reified T> doCall(newCall: Single<T>): Single<T> {
        return newCall
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { Timber.e(it, it.localizedMessage)  }
    }
}