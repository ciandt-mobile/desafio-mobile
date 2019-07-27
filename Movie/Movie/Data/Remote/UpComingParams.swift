//
//  DiscoverMoviesParams.swift
//  Movie
//
//  Created by Gabriel Guerrero on 26/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import Foundation

struct UpComingParams: Encodable {
    var apiKey: String
    var page: Int
    
    enum CodingKeys: String, CodingKey {
        case apiKey = "api_key"
        case page = "page"
    }
}
