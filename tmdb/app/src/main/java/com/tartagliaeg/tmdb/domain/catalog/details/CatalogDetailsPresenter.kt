package com.tartagliaeg.tmdb.domain.catalog.details

import com.tartagliaeg.tmdb.domain.catalog.Movie
import com.tartagliaeg.tmdb.domain.catalog.MovieImages
import com.tartagliaeg.tmdb.domain.catalog.interactor.resolveImagesForCatalogPage
import com.tartagliaeg.tmdb.domain.catalog.interactor.resolveImagesForMovie
import com.tartagliaeg.tmdb.domain.catalog.interactor.retrieveMovieDetailsInteractor
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class CatalogDetailsPresenter : CatalogDetailsPresenterContract {
    override lateinit var module: CatalogDetailsModuleContract
    lateinit var subscription: Disposable

    override fun didRequestLoadDetails(id: Int, images: MovieImages) {
        Single.just(module.repository)
            .compose(retrieveMovieDetailsInteractor(id))
            .compose(resolveImagesForMovie(images))
            .subscribe(object : SingleObserver<Movie> {
                override fun onSuccess(t: Movie) {
                    module.view.showDetailsContent(t)
                }

                override fun onSubscribe(d: Disposable) {
                    subscription = d
                    module.view.showDetailsLoading()
                }

                override fun onError(e: Throwable) {
                    module.view.showDetailsFailure(e.message ?: "Something went wrong...")
                }
            })
    }

    override fun didRequestLoadCancellation() {
        subscription.dispose()
    }

}


