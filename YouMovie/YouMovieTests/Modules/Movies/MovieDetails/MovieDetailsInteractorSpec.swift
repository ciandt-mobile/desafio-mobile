//
//  MovieDetailsInteractorSpec.swift
//  YouMovieTests
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation
import Quick
import Nimble
@testable import YouMovie

class MovieDetailsInteractorSpec: QuickSpec {

    override func spec() {

        var sut: MovieDetailsInteractor!
        var output: MovieDetailsPresenterMock!

        beforeEach {
            output = MovieDetailsPresenterMock()
        }

        afterEach {
            sut = nil
            output = nil
        }

        context("API Success Called") {

            it("Should call fetchMoviesDidSucceed on output") {
                let movie = MovieEntity()
                movie.id = 0

                sut = MovieDetailsInteractor(movie: movie)
                sut.requests = MovieDetailsRequestsSuccessMock()
                sut.output = output
                sut.fetchMovieDetails()
                expect(output.fetchMovieDetailsDidSucceedCalled).to(beTrue())
            }
        }

        context("API Failed Called") {

            it("Should call fetchMoviesDidFailed on output") {
                let movie = MovieEntity()
                movie.id = 0

                sut = MovieDetailsInteractor(movie: movie)
                sut.requests = MovieDetailsRequestsFailureMock()
                sut.output = output
                sut.fetchMovieDetails()
                expect(output.fetchMovieDetailsDidFailedCalled).to(beTrue())
            }
        }
    }
}
