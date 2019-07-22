//
//  Movie.swift
//  MovieApp
//
//  Created by Michele de Olivio Corrêa on 19/07/19.
//  Copyright © 2019 Michele de Olivio Corrêa. All rights reserved.
//

import Foundation

public struct MoviesResponse: Codable {
    public let page: Int
    public let totalResults: Int
    public let totalPages: Int
    public let results: [Movie]
    
    enum CodingKeys: String, CodingKey {
        case page, results
        case totalPages = "total_pages"
        case totalResults = "total_results"
    }
}

public struct Movie: Codable {
    public let id: Int
    public let title: String
    public let backdropPath: String?
    public let posterPath: String?
    public let releaseDate: String
    public let genres: [MovieGenre]?
    public let overview: String
    public let credits: MovieCreditResponse?
    public let runtime: Int?

    enum CodingKeys: String, CodingKey {
        case id, title, genres, overview, credits, runtime
        case backdropPath = "backdrop_path"
        case posterPath = "poster_path"
        case releaseDate = "release_date"
    }
    
    public var posterURL: URL {
        return URL(string: "https://image.tmdb.org/t/p/w185\(posterPath ?? "")")!
    }
    
    public var backdropURL: URL {
        return URL(string: "https://image.tmdb.org/t/p/w500\(backdropPath ?? "")")!
    }
    
    public struct MovieGenre: Codable {
        let name: String
    }
    
    public struct MovieCreditResponse: Codable {
        public let cast: [MovieCast]
    }
    
    public struct MovieCast: Codable {
        public let character: String
        public let name: String
        public let profilePath: String?
        
        enum CodingKeys: String, CodingKey {
            case name, character
            case profilePath = "profile_path"
        }
        
        public var profileURL: URL {
            return URL(string: "https://image.tmdb.org/t/p/w185\(profilePath ?? "")")!
        }
    }

}
