package com.tartagliaeg.tmdb.domain.catalog.listing

import com.tartagliaeg.tmdb.domain.catalog.Movie
import com.tartagliaeg.tmdb.domain.catalog.MovieImages
import com.tartagliaeg.tmdb.domain.catalog.TMDBPage
import com.tartagliaeg.tmdb.domain.catalog.interactor.resolveImagesForCatalogPage
import com.tartagliaeg.tmdb.domain.catalog.interactor.retrieveCachedUpcomingCatalogPage
import com.tartagliaeg.tmdb.domain.catalog.interactor.sortByDateCatalogPage
import com.tartagliaeg.tmdb.domain.catalog.interactor.sortByPopularityCatalogPage
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class CatalogListingPresenter : CatalogListingPresenterContract {
    override lateinit var module: CatalogListingModuleContract
    lateinit var subscription: Disposable

    override fun didRequestLoadCatalog(page: Int, images: MovieImages, orderedBy: Int) {
        Single.just(module.repository)
            .compose(retrieveCachedUpcomingCatalogPage(page, module.cache))
            .compose(resolveImagesForCatalogPage(images))
            .compose(
                if(orderedBy == CatalogListingConstants.ORDER_BY_POPULARITY) sortByPopularityCatalogPage()
                else sortByDateCatalogPage()
            )
            .subscribe(object:SingleObserver<TMDBPage<Movie>> {
                override fun onSuccess(t: TMDBPage<Movie>) {
                    module.view.showCatalogsContent(CatalogListingViewState(t, orderedBy))
                }

                override fun onSubscribe(d: Disposable) {
                    subscription = d
                    module.view.showCatalogsLoading()
                }

                override fun onError(e: Throwable) {
                    module.view.showCatalogsFailure(e.message?:"Something went wrong...")
                }
            })
    }

    override fun didRequestLoadCancellation() {
        subscription.dispose()
    }

    override fun didTouchOnCatalogItem(movie: Movie) {
        module.detailsNavigation.navigate(module.activity, movie.id)
    }
}

