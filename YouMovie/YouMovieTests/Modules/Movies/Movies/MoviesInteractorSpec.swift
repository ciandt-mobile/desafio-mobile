//
//  MoviesInteractorSpec.swift
//  YouMovieTests
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation
import Quick
import Nimble
@testable import YouMovie

class MoviesInteractorSpec: QuickSpec {

    override func spec() {

        var sut: MoviesInteractor!
        var output: MoviesPresenterMock!

        beforeEach {
            output = MoviesPresenterMock()
        }

        afterEach {
            sut = nil
            output = nil
        }

        context("API Success Called") {

            it("Should call fetchMoviesDidSucceed on output") {
                sut = MoviesInteractor()
                sut.requests = MoviesRequestsSuccessMock()
                sut.output = output
                sut.fetchMovies(from: .popular, atPage: 1)
                expect(output.fetchMoviesDidSucceedCalled).to(beTrue())
            }
        }

        context("API Failed Called") {

            it("Should call fetchMoviesDidFailed on output") {
                sut = MoviesInteractor()
                sut.requests = MoviesRequestsFailureMock()
                sut.output = output
                sut.fetchMovies(from: .popular, atPage: 1)

                expect(output.fetchMoviesDidFailedCalled).to(beTrue())
            }
        }
    }
}
