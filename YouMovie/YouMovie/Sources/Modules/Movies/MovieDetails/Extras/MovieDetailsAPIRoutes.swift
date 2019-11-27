//
//  MovieDetailsAPIRoutes.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation

extension APIRoutes {

    struct MovieDetails {

        static func fetchMovieDetails(byMovieID movieID: Int) -> String {

            let baseURL: String = APIRoutes.apiBaseURL
            let apiKey: String = APIRoutes.apiKey
            var language: String = ""

            if let currentLanguage = Locale.current.collatorIdentifier {
                language = "&language=\(currentLanguage)"
            }

            return "\(baseURL)/movie/\(movieID)?api_key=\(apiKey)\(language)&append_to_response=videos,credits,recommendations"
        }

        static func fetchImage(fromPath path: String, size: ImageSize) -> String {
            return "https://image.tmdb.org/t/p/\(size.rawValue)/\(path)"
        }
    }
}
