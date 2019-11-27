//
//  MovieDetailsRequests.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation
import ObjectMapper

typealias MovieDetailsSuccess = (_ movie: MovieEntity) -> Void

protocol MovieDetailsRequestsProtocol: class {
    func fetchMovieDetails(byMovieID movieID: Int,
                           success: @escaping MovieDetailsSuccess,
                           failure: @escaping RequestFailure)
}

class MovieDetailsRequests: NSObject, MovieDetailsRequestsProtocol {

    // MARK: - Internal Properties

    var provider: NetworkProviderProtocol = NetworkProvider()

    // MARK: - Internal Methods

    func fetchMovieDetails(byMovieID movieID: Int,
                           success: @escaping MovieDetailsSuccess,
                           failure: @escaping RequestFailure) {
        
        let urlString = APIRoutes.MovieDetails.fetchMovieDetails(byMovieID: movieID)

        self.provider.request(urlString: urlString, method: .get, success: { json in

            guard let movie = MovieEntity(JSON: json) else {
                failure(RequestError.error(.internalError))
                return
            }

            success(movie)
        }) { error in
            failure(error)
        }
    }
}
