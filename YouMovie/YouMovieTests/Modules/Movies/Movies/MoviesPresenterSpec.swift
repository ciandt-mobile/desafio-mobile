//
//  MoviesPresenterSpec.swift
//  YouMovieTests
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation
import Quick
import Nimble
@testable import YouMovie

class MoviesPresenterSpec: QuickSpec {

    // swiftlint:disable function_body_length
    override func spec() {

        var wireframe: MoviesWireframeMock!
        var view: MoviesViewMock!
        var sut: MoviesPresenter!
        var interactor: MoviesInteractorMock!

        beforeEach {
            wireframe = MoviesWireframeMock()
            view = MoviesViewMock()
            interactor = MoviesInteractorMock()

            sut = MoviesPresenter()
            sut.wireframe = wireframe
            sut.view = view
            sut.interactor = interactor
        }

        afterEach {
            wireframe = nil
            view = nil
            sut = nil
            interactor = nil
        }

        context("Verify Properties") {

            it("Should Viper Properties not be nil") {
                expect(sut.wireframe).toNot(beNil())
                expect(sut.view).toNot(beNil())
                expect(sut.interactor).toNot(beNil())
            }

            it("Should Title be equal") {
                sut.fetchMovies(from: .popular)
                expect(sut.title).to(equal("Popular"))

                sut.fetchMovies(from: .topRated)
                expect(sut.title).to(equal("Top Rated"))

                sut.fetchMovies(from: .upcoming)
                expect(sut.title).to(equal("Upcoming"))
            }

            it("Should Movies has 2 values") {
                let category = MoviesCategoryEntity()

                let movieOne = MovieEntity()
                movieOne.title = "Movie One"

                let movieTwo = MovieEntity()
                movieTwo.title = "Movie Two"
                category.movies = [movieOne, movieTwo]
                interactor.moviesCategory = category

                sut.searchMovie(byTitle: "Movie")
                expect(sut.movies.count).to(equal(2))
            }

            it("Should Movies has 1 value") {
                let category = MoviesCategoryEntity()

                let movieOne = MovieEntity()
                movieOne.title = "Movie One"

                let movieTwo = MovieEntity()
                movieTwo.title = "Movie Two"
                category.movies = [movieOne, movieTwo]
                interactor.moviesCategory = category

                sut.searchMovie(byTitle: "Movie One")
                expect(sut.movies.count).to(equal(1))
            }

            it("Should Movies has 0 value") {
                let category = MoviesCategoryEntity()

                let movieOne = MovieEntity()
                movieOne.title = "Movie One"

                let movieTwo = MovieEntity()
                movieTwo.title = "Movie Two"
                category.movies = [movieOne, movieTwo]
                interactor.moviesCategory = category

                sut.searchMovie(byTitle: "Teste")
                expect(sut.movies.count).to(equal(0))

                sut.searchMovie(byTitle: "")
                expect(sut.movies.count).to(equal(2))
            }
        }

        context("Called Methods") {

            it("Should fetchMovies be called") {
                sut.fetchMovies(from: .popular)
                expect(interactor.fetchMoviesCalled).to(beTrue())
            }

            it("Should reloadMovies call fetchMovies") {
                sut.reloadMovies()
                expect(interactor.fetchMoviesCalled).to(beTrue())
            }

            it("Should fetchNextPageMovies call fetchMovies") {
                sut.fetchNextPageMovies()
                expect(interactor.fetchMoviesCalled).to(beTrue())
            }

            it("Should searchMovie call reloadMovies") {
                sut.searchMovie(byTitle: "MoviesTest")
                expect(view.reloadMoviesCalled).to(beTrue())
            }

            it("Should didSelectMovie call presentDetails") {
                sut.didSelectMovie(MovieEntity())
                expect(wireframe.presentDetailsCalled).to(beTrue())
            }

            it("Should fetchMoviesDidSucceed call addNewMovies") {
                let category = MoviesCategoryEntity()
                category.page = 2
                interactor.moviesCategory = category

                sut.fetchMoviesDidSucceed()
                expect(view.addNewMoviesCalled).to(beTrue())
            }

            it("Should fetchMoviesDidSucceed call addNewMovies") {
                let category = MoviesCategoryEntity()
                category.page = 1
                interactor.moviesCategory = category

                sut.fetchMoviesDidSucceed()
                expect(view.reloadMoviesCalled).to(beTrue())
            }

            it("Should fetchMoviesDidFailed call didFailedMovies") {
                sut.fetchMoviesDidFailed(.error(.internalError))
                expect(view.didFailedMoviesCalled).to(beTrue())
            }
        }
    }
}
