//
//  MovieDetail.swift
//  ChallengeCIEC
//
//  Created by Guilherme Camilo Rosa on 25/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation

struct MovieDetail: Codable {
    let id: Int?
    let title: String?
    let overview: String?
    let popularity: Double?
    let homepage: String?
    let posterPath: String?
    let backdrop: String?
    let runtime: Int?
    let releaseDate: String?
    let genres: [Genre]?
    
    private enum CodingKeys: String, CodingKey {
        case id = "id"
        case title = "title"
        case overview = "overview"
        case popularity = "popularity"
        case homepage = "homepage"
        case posterPath = "poster_path"
        case backdrop = "backdrop_path"
        case runtime = "runtime"
        case releaseDate = "release_date"
        case genres = "genres"
    }
}
