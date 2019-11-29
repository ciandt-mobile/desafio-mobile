//
//  MoviesViewSpec.swift
//  YouMovieTests
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation
import Quick
import Nimble
@testable import YouMovie

class MoviesViewSpec: QuickSpec {

    // swiftlint:disable function_body_length
    override func spec() {

        var sut: MoviesView!
        var presenter: MoviesPresenterMock!

        beforeEach {
            presenter = MoviesPresenterMock()

            sut = MoviesView(nibName: String(describing: MoviesView.self), bundle: nil)
            sut.presenter = presenter

            UIApplication.shared.windows.first?.rootViewController = sut
        }

        afterEach {
            sut = nil
            presenter = nil
        }

        context("Verify Setup Properties") {

            it("Should Outlets not be nil") {
                sut.viewDidLoad()
                expect(sut.collectionView).toNot(beNil())
            }

            it("Should viewDidLoad add titleView") {
                sut.viewDidLoad()
                expect(sut.navigationItem.titleView).toNot(beNil())
            }

            it("Should viewDidLoad setup SearchController") {
                sut.viewDidLoad()
                expect(sut.navigationItem.searchController).toNot(beNil())
                expect(sut.navigationItem.searchController?.searchBar.delegate?.isKind(of: MoviesView.self)).to(beTrue())
                expect(sut.navigationItem.searchController?.searchBar.tintColor).to(equal(UIColor.Style.blackAdaptative))
                expect(sut.navigationItem.searchController?.obscuresBackgroundDuringPresentation).to(beFalse())
                expect(sut.navigationItem.hidesSearchBarWhenScrolling).toNot(beNil())
            }

            it("Should viewDidLoad setup CollectionView") {
                sut.viewDidLoad()
                expect(sut.collectionView.dataSource?.isKind(of: MoviesView.self)).to(beTrue())
                expect(sut.collectionView.delegate?.isKind(of: MoviesView.self)).to(beTrue())

                expect(sut.collectionView.refreshControl).toNot(beNil())
                expect(sut.collectionView.refreshControl?.tintColor).to(equal(.gray))
            }

            it("Should viewWillAppears setupNavigationBar and Status Bar") {
                sut.viewWillAppear(true)
                expect(sut.statusBarStyle).to(equal(.default))

                presenter.title = "MoviesTest"
                expect(sut.navigationItem.title).toEventually(equal("MoviesTest"))
            }

            it("Should reloadMovies setup behavios") {
                sut.viewDidLoad()
                sut.reloadMovies()
                expect(sut.collectionView.alpha).to(equal(1.0))
                expect(sut.collectionView.refreshControl?.isRefreshing).to(beFalse())
            }
        }

        context("Called Methods") {

            it("Should viewDidLoad call fetchMovies with behaviors") {
                sut.viewDidLoad()
                expect(presenter.fetchMoviesCalled).toEventually(beTrue())

                expect(sut.collectionView.alpha).toEventually(equal(0.0))
                expect(sut.collectionView.contentOffset).toEventually(equal(.zero))

                expect(sut.view.subviews.contains(where: { $0.isKind(of: UIActivityIndicatorView.self) })).toEventually(beTrue())

                presenter.title = "MoviesTest"
                expect(sut.navigationItem.title).toEventually(equal("MoviesTest"))
            }

            it("Should didPullToRefresh not call reloadMovies") {
                sut.viewDidLoad()
                presenter.shouldSearchMovie = true
                sut.didPullToRefresh()
                expect(presenter.reloadMoviesCalled).to(beFalse())
            }

            it("Should didPullToRefresh call reloadMovies") {
                sut.viewDidLoad()
                presenter.shouldSearchMovie = false
                sut.didPullToRefresh()
                expect(presenter.reloadMoviesCalled).to(beTrue())
                expect(sut.view.subviews.contains(where: { $0.isKind(of: UIActivityIndicatorView.self) })).to(beFalse())
            }

            it("Should didFailedMovies present an Alert") {
                sut.viewDidLoad()
                sut.didFailedMovies(localizedError: "MoviesTest")

                let rootView = UIApplication.shared.windows.first?.rootViewController
                expect(rootView?.presentedViewController?.isKind(of: UIAlertController.self)).toEventually(beTrue())
            }
        }

        context("Collection View Setups") {

            it("Should didSelectItemAt call didSelectMovie") {
                sut.viewDidLoad()

                presenter.movies = [MovieEntity()]
                sut.collectionView(sut.collectionView, didSelectItemAt: IndexPath(item: 0, section: 0))

                expect(presenter.didSelectMovieCalled).toEventually(beTrue())
            }

            it("Should insetForSectionAt be equal") {
                sut.viewDidLoad()
                let insets = sut.collectionView(sut.collectionView, layout: UICollectionViewLayout(), insetForSectionAt: 0)

                expect(insets.top).to(equal(15.0))
                expect(insets.left).to(equal(15.0))
                expect(insets.bottom).to(equal(15.0))
                expect(insets.right).to(equal(15.0))
            }

            it("Should margins be equal to lines and Interitem") {
                sut.viewDidLoad()
                let line = sut.collectionView(sut.collectionView, layout: UICollectionViewLayout(), minimumLineSpacingForSectionAt: 0)
                let interitem = sut.collectionView(sut.collectionView, layout: UICollectionViewLayout(), minimumInteritemSpacingForSectionAt: 0)

                expect(line).to(equal(15.0))
                expect(interitem).to(equal(15.0))
            }

            it("Should referenceSizeForFooterInSection be equal to zero") {
                sut.viewDidLoad()
                let size = sut.collectionView(sut.collectionView, layout: UICollectionViewLayout(), referenceSizeForFooterInSection: 0)

                expect(size).to(equal(.zero))
            }

            it("Should referenceSizeForFooterInSection be equal to widht and height") {
                sut.viewDidLoad()
                presenter.shouldFetchNextPageMovies = true
                let size = sut.collectionView(sut.collectionView, layout: UICollectionViewLayout(), referenceSizeForFooterInSection: 0)

                expect(size.width).to(equal(sut.view.frame.size.width))
                expect(size.height).to(equal(40.0))
            }

            it("Should willDisplay cell call fetchNextPage") {
                sut.viewDidLoad()
                presenter.shouldFetchNextPageMovies = true

                let cell = UICollectionViewCell()
                presenter.movies = [MovieEntity()]

                sut.collectionView(sut.collectionView, willDisplay: cell, forItemAt: IndexPath(item: 0, section: 0))

                expect(presenter.fetchNextPageMoviesCalled).to(beTrue())
            }

            it("Should willDisplay cell not call fetchNextPage") {
                sut.viewDidLoad()
                presenter.shouldFetchNextPageMovies = false

                let cell = UICollectionViewCell()
                presenter.movies = [MovieEntity()]

                sut.collectionView(sut.collectionView, willDisplay: cell, forItemAt: IndexPath(item: 0, section: 0))

                expect(presenter.fetchNextPageMoviesCalled).to(beFalse())
            }
        }

        context("Search Bar Behaviors") {

            it("Should searchBarTextDidBeginEditing set shouldSearchMovie true") {
                sut.viewDidLoad()
                sut.searchBarTextDidBeginEditing(sut.navigationItem.searchController!.searchBar)
                expect(presenter.shouldSearchMovie).to(beTrue())
            }

            it("Should searchBarCancelButtonClicked set shouldSearchMovie true") {
                sut.viewDidLoad()
                sut.searchBarCancelButtonClicked(sut.navigationItem.searchController!.searchBar)
                expect(presenter.shouldSearchMovie).to(beFalse())
            }

            it("Should searchBar call searchMovie") {
                sut.viewDidLoad()
                sut.searchBar(sut.navigationItem.searchController!.searchBar, textDidChange: "MoviesText")
                expect(presenter.searchMovieCalled).to(beTrue())
            }
        }
    }
}
