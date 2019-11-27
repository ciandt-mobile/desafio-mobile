//
//  MoviesAPIRoutes.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation

extension APIRoutes {

    struct Movies {

        static func fetchMovies(from section: MoviesSectionType, atPage page: Int) -> String {

            var sectionString: String = ""
            let baseURL: String = APIRoutes.apiBaseURL
            let apiKey: String = APIRoutes.apiKey
            var language: String = ""

            switch section {
            case .popular:
                sectionString = "popular"
            case .topRated:
                sectionString = "top_rated"
            case .upcoming:
                sectionString = "upcoming"
            }

            if let currentLanguage = Locale.current.collatorIdentifier {
                language = "&language=\(currentLanguage)"
            }

            return "\(baseURL)/movie/\(sectionString)?api_key=\(apiKey)\(language)&page=\(page)"
        }

        static func fetchImage(fromPath path: String, size: ImageSize) -> String {
            return "https://image.tmdb.org/t/p/\(size.rawValue)/\(path)"
        }
    }
}
