//
//  Constants.swift
//  Movie
//
//  Created by Gabriel Guerrero on 25/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import Foundation


struct HTTP {
    static let API_KEY = "edec5ecbf2207c84bd611d0e415f9f87"
    static let language = "en-US"
    static let credits = "credits"
    static let baseURL = "https://api.themoviedb.org/3/"
    static let baseImageURL185 = "http://image.tmdb.org/t/p/w185/"
    static let baseImageURL500 = "http://image.tmdb.org/t/p/w500/"
    
    struct PATHS {
        static let upComing = "movie/upcoming"
        static let popular = "movie/popular"
        static let movieDetails = "movie/"
    }
}

enum HTTPHeader: String {
    case authorization = "Authorization"
    case contentType = "Content-Type"
    case acceptType = "Accept"
    case acceptEncoding = "Accept-Encoding"
    case json = "application/json"
}
