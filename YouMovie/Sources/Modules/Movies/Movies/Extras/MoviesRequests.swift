//
//  MoviesRequests.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation
import ObjectMapper

typealias MoviesSuccess = (_ moviesCategory: MoviesCategoryEntity) -> Void

protocol MoviesRequestsProtocol: class {
    func fetchMovies(from section: MoviesSectionType,
                     atPage page: Int,
                     success: @escaping MoviesSuccess,
                     failure: @escaping RequestFailure)
}

class MoviesRequests: NSObject, MoviesRequestsProtocol {

    // MARK: - Internal Properties

    var provider: NetworkProviderProtocol = NetworkProvider()

    // MARK: - Internal Methods

    func fetchMovies(from section: MoviesSectionType,
                     atPage page: Int,
                     success: @escaping MoviesSuccess,
                     failure: @escaping RequestFailure) {

        let urlString = APIRoutes.Movies.fetchMovies(from: section, atPage: page)

        self.provider.request(urlString: urlString, method: .get, success: { json in

            guard let moviesCategory = MoviesCategoryEntity(JSON: json) else {
                failure(RequestError.error(.internalError))
                return
            }

            success(moviesCategory)
        }) { error in
            failure(error)
        }
    }
}
