//
//  Movie.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 30/11/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import Foundation

struct MovieResult : Decodable {
    var page : Int?
    var totalResults : Int?
    var totalPages : Int?
    var results : [Movie]
}

struct Movie: Decodable {
    let id : Int?
    var title : String?
    let popularity : Double?
    let overview : String?

    let voteCount : Int?
    let voteAverage : Double?
    let posterPath : String?
    let originalLanguage : String?
    let originalTitle : String?
    let genreIds : [Int]?
    let backdropPath : String?
    let releaseDate : String?
}
