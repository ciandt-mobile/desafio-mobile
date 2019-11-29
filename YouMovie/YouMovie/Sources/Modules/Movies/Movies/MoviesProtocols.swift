//
//  MoviesProtocols.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

// MARK: - Wireframe

protocol MoviesWireframeProtocol: class {
    func instantiateView() -> MoviesView
    func presentDetails(from movie: MovieEntity)
}

// MARK: - View

protocol MoviesPresenterOutputProtocol: class {
    func reloadMovies()
    func addNewMovies()
    func didFailedMovies(localizedError: String)
}

// MARK: - Presenter

protocol MoviesPresenterInputProtocol: class {
    var movies: [MovieEntity] { get }
    var shouldFetchNextPageMovies: Bool { get }
    var shouldSearchMovie: Bool { get set }
    var title: String { get }
    var currentSection: MoviesSectionType { get }
    func fetchMovies(from section: MoviesSectionType)
    func reloadMovies()
    func fetchNextPageMovies()
    func searchMovie(byTitle title: String)
    func didSelectMovie(_ movie: MovieEntity)
}

protocol MoviesInteractorOutputProtocol: class {
    func fetchMoviesDidSucceed()
    func fetchMoviesDidFailed(_ error: RequestError)
}

// MARK: - Interactor

protocol MoviesInteractorInputProtocol: class {
    var moviesCategory: MoviesCategoryEntity? { get }
    func fetchMovies(from section: MoviesSectionType, atPage page: Int)
}
