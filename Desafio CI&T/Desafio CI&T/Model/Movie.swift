//
//  Movie.swift
//  Desafio CI&T
//
//  Created by Mateus Kamoei on 30/07/19.
//  Copyright © 2019 Mateus Kamoei. All rights reserved.
//

import Foundation

class Movie {
    
    let title: String
    let releaseDate: Date
    let posterPath: String
    let genres: [String]
    let runtime: Int
    let overview: String
    
    init(title: String, releaseDate: Date, posterPath: String, genres: [String], runtime: Int, overview: String) {
        self.title = title
        self.releaseDate = releaseDate
        self.posterPath = posterPath
        self.genres = genres
        self.runtime = runtime
        self.overview = overview
    }
    
}
