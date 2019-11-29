//
//  MoviesMocks.swift
//  YouMovieTests
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation
@testable import YouMovie

// MARK: - MoviesWireframeMock

class MoviesWireframeMock: MoviesWireframeProtocol {

    // MARK: - Internal Properties

    var instantiateViewCalled: Bool = false
    var presentDetailsCalled: Bool = false

    // MARK: - Internal Methods

    func instantiateView() -> MoviesView {
        self.instantiateViewCalled = true
        return MoviesView()
    }

    func presentDetails(from movie: MovieEntity) {
        self.presentDetailsCalled = true
    }
}

// MARK: - MoviesViewMock

class MoviesViewMock: MoviesPresenterOutputProtocol {

    // MARK: - Internal Properties

    var reloadMoviesCalled: Bool = false
    var addNewMoviesCalled: Bool = false
    var didFailedMoviesCalled: Bool = false

    // MARK: - Internal Methods

    func reloadMovies() {
        self.reloadMoviesCalled = true
    }

    func addNewMovies() {
        self.addNewMoviesCalled = true
    }

    func didFailedMovies(localizedError: String) {
        self.didFailedMoviesCalled = true
    }
}

// MARK: - MoviesPresenterMock

class MoviesPresenterMock: MoviesPresenterInputProtocol, MoviesInteractorOutputProtocol {

    // MARK: - Internal Properties

    var fetchMoviesCalled: Bool = false
    var reloadMoviesCalled: Bool = false
    var fetchNextPageMoviesCalled: Bool = false
    var searchMovieCalled: Bool = false
    var didSelectMovieCalled: Bool = false
    var fetchMoviesDidSucceedCalled: Bool = false
    var fetchMoviesDidFailedCalled: Bool = false

    var movies: [MovieEntity] = []
    var shouldFetchNextPageMovies: Bool = false
    var shouldSearchMovie: Bool = false
    var title: String = ""
    var currentSection: MoviesSectionType = .popular

    // MARK: - Internal Methods

    func fetchMovies(from section: MoviesSectionType) {
        self.fetchMoviesCalled = true
    }

    func reloadMovies() {
        self.reloadMoviesCalled = true
    }

    func fetchNextPageMovies() {
        self.fetchNextPageMoviesCalled = true
    }

    func searchMovie(byTitle title: String) {
        self.searchMovieCalled = true
    }

    func didSelectMovie(_ movie: MovieEntity) {
        self.didSelectMovieCalled = true
    }

    func fetchMoviesDidSucceed() {
        self.fetchMoviesDidSucceedCalled = true
    }

    func fetchMoviesDidFailed(_ error: RequestError) {
        self.fetchMoviesDidFailedCalled = true
    }
}

// MARK: - MoviesInteractorMock

class MoviesInteractorMock: MoviesInteractorInputProtocol {

    // MARK: - Internal Properties

    var fetchMoviesCalled: Bool = false

    var moviesCategory: MoviesCategoryEntity? = MoviesCategoryEntity()

    // MARK: - Internal Methods

    func fetchMovies(from section: MoviesSectionType, atPage page: Int) {
        self.fetchMoviesCalled = true
    }
}

// MARK: - MoviesRequestsSuccessMock

class MoviesRequestsSuccessMock: MoviesRequestsProtocol {

    // MARK: - Internal Methods

    func fetchMovies(from section: MoviesSectionType,
                     atPage page: Int,
                     success: @escaping MoviesSuccess,
                     failure: @escaping RequestFailure) {
        let category = MoviesCategoryEntity()
        category.page = 1
        category.movies = []
        success(category)
    }
}

// MARK: - MoviesRequestsFailureMock

class MoviesRequestsFailureMock: MoviesRequestsProtocol {

    // MARK: - Internal Methods

    func fetchMovies(from section: MoviesSectionType,
                     atPage page: Int,
                     success: @escaping MoviesSuccess,
                     failure: @escaping RequestFailure) {
        failure(.error(.internalError))
    }
}
