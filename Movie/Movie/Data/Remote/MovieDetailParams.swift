//
//  MovieDetailParams.swift
//  Movie
//
//  Created by Gabriel Guerrero on 26/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import Foundation

struct MovieDetailParams: Encodable {
    var apiKey: String
    var appendToResponse: String
    
    enum CodingKeys: String, CodingKey {
        case apiKey = "api_key"
        case appendToResponse = "append_to_response"
    }
}
