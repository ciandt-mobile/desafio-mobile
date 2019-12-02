//
//  MovieDetails.swift
//  movies
//
//  Created by Arthur Silva on 11/30/19.
//  Copyright © 2019 Arthur Silva. All rights reserved.
//

import Foundation

struct MovieDetails: Codable {
    let id: Int
    let genres: [Genre]
    let budget: Int
    let revenue: Int
    let runtime: Int?
    let status: String
    let tagline: String
}
