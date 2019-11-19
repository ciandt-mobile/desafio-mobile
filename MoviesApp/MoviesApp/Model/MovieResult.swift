//
//  MovieResult.swift
//  MoviesApp
//
//  Created by Luan Audibert on 11/18/19.
//  Copyright © 2019 Luan Audibert. All rights reserved.
//

import Foundation
import RxCocoa

struct MovieResult: Decodable, Encodable {
    let popularity: Double
    let voteCount: Int
    let video: Bool
    let posterPath: String
    let id: Int
    let adult: Bool
    let backdropPath: String
    let originalLanguage: String
    let genreIds: [Int]
    let title: String
    let releaseDate: Date
}
