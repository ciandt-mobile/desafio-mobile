package com.tartagliaeg.tmdb.tools

import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxTools {
    fun <T>createScheduledPipe(single: Single<T>, inScheduler: Scheduler, outScheduler: Scheduler ): Single<T> {
        return Single.just(1)
            .observeOn(inScheduler)
            .flatMap { single }
            .observeOn(outScheduler)
    }
    fun <T> ioToMainPipe(single: Single<T>): Single<T> {
        return createScheduledPipe(single, Schedulers.io(), AndroidSchedulers.mainThread())
    }

}