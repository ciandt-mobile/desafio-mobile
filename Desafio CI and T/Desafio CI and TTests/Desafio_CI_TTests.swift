//
//  Desafio_CI_TTests.swift
//  Desafio CI&TTests
//
//  Created by Mateus Kamoei on 30/07/19.
//  Copyright © 2019 Mateus Kamoei. All rights reserved.
//

import XCTest
@testable import Desafio_CI_and_T

class Desafio_CI_TTests: XCTestCase {

    var network: Network!
    var moviesViewModel: MoviesViewModel!
    var movieDetailsViewModel: MovieDetailsViewModel!
    
    var moviesJson: [[String: Any]]?
    var movieDetailsJson: [String: Any]?
    var movieCreditsJson: [String: Any]?
    var movies: [Movie]?
    var moviesExpectation: XCTestExpectation?
    var movieDetailsExpectation: XCTestExpectation?
    var movieCreditsExpectation: XCTestExpectation?
    var moviesParsingExpectation: XCTestExpectation?
    var moviesFilterExpectation: XCTestExpectation?
    
    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.
        network = Network(delegate: self)
        moviesViewModel = MoviesViewModel(delegate: self)
        movieDetailsViewModel = MovieDetailsViewModel(Movie(id: 420818, title: "A movie", releaseDate: Date(), posterPath: ""), delegate: self)
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testGetPopularMoviesSuccessReturnMovies() {
        moviesExpectation = expectation(description: "movies")
        network.getPopularMovies()
        waitForExpectations(timeout: 10) { (error) in
            XCTAssertNotNil(self.moviesJson)
        }
    }
    
    func testGetMovieDetailsSuccess() {
        movieDetailsExpectation = expectation(description: "movieDetails")
        network.getMovieDetails(420818)
        waitForExpectations(timeout: 10) { (error) in
            XCTAssertNotNil(self.movieDetailsJson)
        }
    }
    
    func testGetMovieCreditsSuccess() {
        movieCreditsExpectation = expectation(description: "movieCredits")
        network.getMovieCredits(420818)
        waitForExpectations(timeout: 10) { (error) in
            XCTAssertNotNil(self.movieCreditsJson)
        }
    }
    
    func testUpcomingMoviesFilter() {
        moviesFilterExpectation = expectation(description: "moviesFilter")
        moviesViewModel.getPopularMovies()
        waitForExpectations(timeout: 10, handler: nil)
    }
}

extension Desafio_CI_TTests: NetworkDelegate {
    
    func didFinishGettingPopularMovies(_ dictionary: [String : Any]) {
        guard let movies = dictionary["results"] as? [[String: Any]] else { return }
        self.moviesJson = movies
        self.moviesExpectation?.fulfill()
        
        if movies.count > 0 {
            self.movies = [Movie]()
            for movieJson in movies {
                let movie = Movie(movieJson)
                self.movies?.append(movie)
            }
            XCTAssertGreaterThan(self.movies!.count, 0)
        }
    }
    
    func didFinishGettingMovieDetails(_ dictionary: [String : Any]) {
        self.movieDetailsJson = dictionary
        self.movieDetailsExpectation?.fulfill()
        let movie = Movie(id: 0, title: "Test", releaseDate: Date(), posterPath: "")
        movie.setMovieDetails(dictionary)
        XCTAssertNotNil(movie.overview!)
    }
    
    func didFinishGettingMovieCredits(_ dictionary: [String : Any]) {
        self.movieCreditsJson = dictionary
        self.movieCreditsExpectation?.fulfill()
        let movie = Movie(id: 0, title: "Test", releaseDate: Date(), posterPath: "")
        movie.setCast(dictionary)
        XCTAssertGreaterThan(movie.cast!.count, 0)
    }
}

extension Desafio_CI_TTests: MoviesViewModelDelegate {
    func didFinishGettingPopularMovies(_ viewModel: MoviesViewModel, dictionary: [String : Any]) {
        if let moviesFilterExpectation = self.moviesFilterExpectation {
            let moviesCount = viewModel.moviesCount
            viewModel.toggleUpcomingMoviesFilter()
            let filteredMoviesCount = viewModel.moviesCount
            XCTAssertNotEqual(moviesCount, filteredMoviesCount)
            moviesFilterExpectation.fulfill()
        }
    }
    
    func didFailGettingPopularMovies(_ viewModel: MoviesViewModel, error: Error?) {
        
    }
}

extension Desafio_CI_TTests: MovieDetailsViewModelDelegate {
    func didFailGettingMovieDetails(_ viewModel: MovieDetailsViewModel, error: Error?) {
        
    }
    
    func didFinishGettingImage(_ viewModel: MovieDetailsViewModel) {
        
    }
    
    func didFinishGettingMovieDetails(_ viewModel: MovieDetailsViewModel) {
        
    }
    
    func didFailGettingMovieCredits(_ viewModel: MovieDetailsViewModel, error: Error?) {
        
    }
    
    func didFinishGettingMovieCredits(_ viewModel: MovieDetailsViewModel) {
        
    }
    
    func didFailGettingMovieCredits(_ error: Error?) {
        
    }
}
