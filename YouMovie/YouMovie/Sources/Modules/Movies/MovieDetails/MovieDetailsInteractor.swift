//
//  MovieDetailsInteractor.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation

class MovieDetailsInteractor: NSObject {

    // MARK: - Viper Properties

    weak var output: MovieDetailsInteractorOutputProtocol!

    // MARK: - Internal Properties

    var requests: MovieDetailsRequestsProtocol = MovieDetailsRequests()
    
    var movie: MovieEntity

    // MARK: - Inits

    init(movie: MovieEntity) {
        self.movie = movie
    }
}

// MARK: - MovieDetailsInteractorInputProtocol

extension MovieDetailsInteractor: MovieDetailsInteractorInputProtocol {

    // MARK: - Internal Methods

    func fetchMovieDetails() {

        guard let movieID = self.movie.id else {
            self.output.fetchMovieDetailsDidFailed(.error(.internalError))
            return
        }

        self.requests.fetchMovieDetails(byMovieID: movieID, success: { [weak self] movie in
            self?.movie = movie
            self?.output.fetchMovieDetailsDidSucceed()
        }) { [weak self] error in
            self?.output.fetchMovieDetailsDidFailed(error)
        }
    }
}
