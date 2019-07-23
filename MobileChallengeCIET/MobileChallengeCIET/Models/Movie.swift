//
//  Movie.swift
//  MobileChallengeCIET
//
//  Created by Guilherme C Rosa on 22/07/19.
//  Copyright © 2019 Guilherme C Rosa. All rights reserved.
//

import Foundation

struct MovieResult: Codable {
    let page: Int?
    let totalPages: Int?
    let totalResult: Int?
    let movies: [Movie]
    
    private enum CodingKeys: String, CodingKey {
        case page = "page"
        case totalPages = "total_pages"
        case movies = "results"
        case totalResult = "total_results"
    }
}

struct Movie: Codable {
    
    let id: Int?
    let title: String?
    let poster: String?
    let releaseDate: String?
    let overview: String?
    
    private enum CodingKeys: String, CodingKey {
        case id = "id"
        case title = "title"
        case poster = "poster_path"
        case releaseDate = "release_date"
        case overview = "overview"
    }
}
