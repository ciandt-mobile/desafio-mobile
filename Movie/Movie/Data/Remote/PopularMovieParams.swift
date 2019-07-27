//
//  PopularMovieParams.swift
//  Movie
//
//  Created by Gabriel Guerrero on 27/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import Foundation

struct PopularMovieParams: Encodable {
    var apiKey: String
    var page: Int
    
    enum CodingKeys: String, CodingKey {
        case apiKey = "api_key"
        case page = "page"
    }
}
