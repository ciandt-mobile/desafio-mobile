//
//  PopularMovies.swift
//  movies
//
//  Created by Arthur Silva on 11/29/19.
//  Copyright © 2019 Arthur Silva. All rights reserved.
//

import Foundation

struct PopularMovies: Codable {
    let page: Int
    let total_pages: Int
    let results: [Movie]
}
