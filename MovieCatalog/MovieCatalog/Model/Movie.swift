//
//  PopularMovies.swift
//  MovieCatalog
//
//  Created by Vitor Antonio Terzariol Terribile on 20/11/19.
//  Copyright © 2019 Vitor Antonio Terzariol Terribile. All rights reserved.
//

import Foundation
import UIKit


struct Movie: Codable {
    let originalTitle: String?
    var posterPath: String
    let releaseDate: String?
    let movieId: Int?

    enum CodingKeys: String, CodingKey {
        case originalTitle = "original_title"
        case posterPath = "poster_path"
        case releaseDate = "release_date"
        case movieId = "id"
    }
}

struct Response: Codable {
    var results:[Movie]
}

