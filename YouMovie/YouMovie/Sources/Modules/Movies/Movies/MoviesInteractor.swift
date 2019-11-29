//
//  MoviesInteractor.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation

class MoviesInteractor: NSObject {

    // MARK: - Viper Properties

    weak var output: MoviesInteractorOutputProtocol!

    // MARK: - Internal Properties

    var requests: MoviesRequestsProtocol = MoviesRequests()

    // MARK: - Private Properties

    private(set) var moviesCategory: MoviesCategoryEntity?

}

// MARK: - MoviesInteractorInputProtocol

extension MoviesInteractor: MoviesInteractorInputProtocol {

    // MARK: - Internal Methods

    func fetchMovies(from section: MoviesSectionType, atPage page: Int) {
        self.requests.fetchMovies(from: section, atPage: page, success: { [weak self] moviesCategory in

            guard let page = moviesCategory.page,
                let movies = moviesCategory.movies else {
                    self?.output.fetchMoviesDidFailed(.error(.internalError))
                    return
            }

            if page > 1 {
                var currentMovies: [MovieEntity] = self?.moviesCategory?.movies ?? []
                currentMovies.append(contentsOf: movies)
                self?.moviesCategory = moviesCategory
                self?.moviesCategory?.movies = currentMovies
            } else {
                self?.moviesCategory = moviesCategory
            }

            self?.output.fetchMoviesDidSucceed()
        }) { [weak self] error in
            self?.moviesCategory = nil
            self?.output.fetchMoviesDidFailed(error)
        }
    }
}
