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
import OHHTTPStubs
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

            it("Should call fetchMovieDetailsDidSucceedCalled on output") {
                let movie = MovieEntity()
                movie.id = 0

                sut = MovieDetailsInteractor(movie: movie)
                sut.requests = MovieDetailsRequestsSuccessMock()
                sut.output = output
                sut.fetchMovieDetails()
                expect(output.fetchMovieDetailsDidSucceedCalled).to(beTrue())
            }
            
            it("Should call fetchMoviesDidSucceed on output with Stub") {
                
                stub(condition: isMethodGET() && isHost("api.themoviedb.org")) { _ in
                  return OHHTTPStubsResponse(
                    fileAtPath: OHPathForFile("MovieDetailsJSON.json", type(of: self))!,
                    statusCode: 200,
                    headers: ["Content-Type": "application/json"]
                  )
                }
                
                let movie = MovieEntity()
                movie.id = 1234

                sut = MovieDetailsInteractor(movie: movie)
                sut.output = output
                sut.fetchMovieDetails()
                expect(output.fetchMovieDetailsDidSucceedCalled).toEventually(beTrue(), timeout: 5.5, pollInterval: 0.2)
            }
        }

        context("API Failed Called") {

            it("Should call fetchMovieDetailsDidFailedCalled on output with ID") {
                let movie = MovieEntity()
                movie.id = 0

                sut = MovieDetailsInteractor(movie: movie)
                sut.requests = MovieDetailsRequestsFailureMock()
                sut.output = output
                sut.fetchMovieDetails()
                expect(output.fetchMovieDetailsDidFailedCalled).to(beTrue())
            }
            
            it("Should call fetchMovieDetailsDidFailedCalled on output without ID") {
                let movie = MovieEntity()

                sut = MovieDetailsInteractor(movie: movie)
                sut.requests = MovieDetailsRequestsSuccessMock()
                sut.output = output
                sut.fetchMovieDetails()
                expect(output.fetchMovieDetailsDidFailedCalled).to(beTrue())
            }
            
            it("Should call fetchMovieDetailsDidFailedCalled on output with Error") {
                
                stub(condition: isMethodGET() && isHost("api.themoviedb.org")) { _ in
                  return OHHTTPStubsResponse(
                    jsonObject: [:],
                    statusCode: 502,
                    headers: ["Content-Type": "application/json"]
                  )
                }
                
                let movie = MovieEntity()
                movie.id = 1234

                sut = MovieDetailsInteractor(movie: movie)
                sut.output = output
                sut.fetchMovieDetails()
                expect(output.fetchMovieDetailsDidFailedCalled).toEventually(beTrue(), timeout: 5.5, pollInterval: 0.2)
            }
        }
    }
}
