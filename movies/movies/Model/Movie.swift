//
//  Movie.swift
//  movies
//
//  Created by Arthur Silva on 11/29/19.
//  Copyright © 2019 Arthur Silva. All rights reserved.
//

import Foundation

struct Movie: Codable {
    let id: Int
    let poster_path: String
    let adult: Bool
    let backdrop_path: String
    let original_language: String
    let original_title: String
    let genre_ids: [Int]
    let title: String
    let vote_average: Double
    let overview: String
    let release_date: String
}
