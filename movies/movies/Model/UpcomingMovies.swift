//
//  UpcomingMovies.swift
//  movies
//
//  Created by Arthur Silva on 11/29/19.
//  Copyright © 2019 Arthur Silva. All rights reserved.
//

import Foundation

struct UpcomingMovies: Codable {
    let page: Int
    let total_pages: Int
    let dates: DateRange
    let results: [Movie]
    let total_results: Int
}
