//
//  MoviesPresenter.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation

// MARK: - Definitions

enum MoviesSectionType: Int {
    case popular
    case topRated
    case upcoming
}

class MoviesPresenter: NSObject {

    // MARK: - Viper Properties

    var wireframe: MoviesWireframeProtocol!
    weak var view: MoviesPresenterOutputProtocol!
    var interactor: MoviesInteractorInputProtocol!

    // MARK: - Internal Properties

    var movies: [MovieEntity] {
        guard let movies = self.interactor.moviesCategory?.movies else { return [] }

        if self.searchMovieTitle.isEmpty {
            return movies
        } else {
            return movies.filter({ $0.title?.lowercased().contains(self.searchMovieTitle) ?? false })
        }
    }

    var shouldFetchNextPageMovies: Bool {
        let totalPages: Int? = self.interactor.moviesCategory?.totalPages
        let isConnectedToInternet: Bool = Connectivity.isConnectedToInternet
        return !self.isLoading && self.currentPage != totalPages && !self.shouldSearchMovie && isConnectedToInternet
    }

    var shouldSearchMovie: Bool = false {
        didSet {
            guard !self.shouldSearchMovie else { return }
            self.searchMovieTitle = ""
        }
    }

    var title: String {
        switch self.currentSection {
        case .popular:
            return MessagesUtil.Movies.popularTitle
        case .topRated:
            return MessagesUtil.Movies.topRatedTitle
        case .upcoming:
            return MessagesUtil.Movies.upcomingTitle
        }
    }

    // MARK: - Private Properties

    private(set) var currentSection: MoviesSectionType = .popular {
        didSet {
            self.isLoading = true
            self.interactor.fetchMovies(from: self.currentSection, atPage: 1)
        }
    }

    private var currentPage: Int {
        return self.interactor.moviesCategory?.page ?? 1
    }

    private var searchMovieTitle: String = "" {
        didSet {
            self.view.reloadMovies()
        }
    }

    private var isLoading: Bool = false
}

// MARK: - MoviesPresenterInputProtocol

extension MoviesPresenter: MoviesPresenterInputProtocol {

    // MARK: - Internal Methods

    func fetchMovies(from section: MoviesSectionType) {
        self.currentSection = section
    }

    func reloadMovies() {
        self.isLoading = true
        self.interactor.fetchMovies(from: self.currentSection, atPage: 1)
    }

    func fetchNextPageMovies() {
        self.isLoading = true
        self.interactor.fetchMovies(from: self.currentSection, atPage: self.currentPage + 1)
    }

    func searchMovie(byTitle title: String) {
        self.searchMovieTitle = title.lowercased()
    }

    func didSelectMovie(_ movie: MovieEntity) {
        self.wireframe.presentDetails(from: movie)
    }
}

// MARK: - MoviesInteractorOutputProtocol

extension MoviesPresenter: MoviesInteractorOutputProtocol {

    // MARK: - Internal Methods

    func fetchMoviesDidSucceed() {

        self.isLoading = false

        if self.currentPage > 1 {
            self.view.addNewMovies()
        } else {
            self.view.reloadMovies()
        }
    }

    func fetchMoviesDidFailed(_ error: RequestError) {
        self.isLoading = false

        if error.type == .networkError {
            self.view.didFailedMovies(localizedError: error.localizedDescription)
        } else {
            self.view.didFailedMovies(localizedError: MessagesUtil.General.oops)
        }
    }
}
