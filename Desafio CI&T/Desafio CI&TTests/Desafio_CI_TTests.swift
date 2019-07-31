//
//  Desafio_CI_TTests.swift
//  Desafio CI&TTests
//
//  Created by Mateus Kamoei on 30/07/19.
//  Copyright © 2019 Mateus Kamoei. All rights reserved.
//

import XCTest
@testable import Desafio_CI_T

class Desafio_CI_TTests: XCTestCase {

    var network: Network!
    
    var movies: [[String: Any]]?
    var movieDetails: [String: Any]?
    var movieCredits: [String: Any]?
    var moviesExpectation: XCTestExpectation?
    var movieDetailsExpectation: XCTestExpectation?
    var movieCreditsExpectation: XCTestExpectation?
    
    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.
        network = Network(delegate: self)
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testGetPopularMoviesSuccessReturnMovies() {
        moviesExpectation = expectation(description: "movies")
        network.getPopularMovies()
        waitForExpectations(timeout: 10) { (error) in
            XCTAssertNotNil(self.movies)
        }
    }
    
    func testGetMovieDetailsSuccess() {
        movieDetailsExpectation = expectation(description: "movieDetails")
        network.getMovieDetails(420818)
        waitForExpectations(timeout: 10) { (error) in
            XCTAssertNotNil(self.movieDetails)
        }
    }
    
    func testGetMovieCreditsSuccess() {
        movieCreditsExpectation = expectation(description: "movieCredits")
        network.getMovieCredits(420818)
        waitForExpectations(timeout: 10) { (error) in
            XCTAssertNotNil(self.movieCredits)
        }
    }
}

extension Desafio_CI_TTests: NetworkDelegate {
    
    func didFinishGettingPopularMovies(_ dictionary: [String : Any]) {
        guard let movies = dictionary["results"] as? [[String: Any]] else { return }
        self.movies = movies
        self.moviesExpectation?.fulfill()
    }
    
    func didFinishGettingMovieDetails(_ dictionary: [String : Any]) {
        self.movieDetails = dictionary
        self.movieDetailsExpectation?.fulfill()
    }
    
    func didFinishGettingMovieCredits(_ dictionary: [String : Any]) {
        self.movieCredits = dictionary
        self.movieCreditsExpectation?.fulfill()
    }
}
