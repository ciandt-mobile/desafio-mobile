//
//  ServicesConstants.swift
//  movies
//
//  Created by Arthur Silva on 11/29/19.
//  Copyright © 2019 Arthur Silva. All rights reserved.
//

import Foundation
import Alamofire

struct ServicesConstants {

    // MARK: Paths
    static let MOVIES_DB_BASE_PATH = "https://api.themoviedb.org/3"
    static let POPULAR_MOVIES_PATH = "/movie/popular"
    static let UPCOMING_MOVIES_PATH = "/movie/upcoming"
    static let IMAGE_LOW_RESOLUTION_BASE_PATH = "https://image.tmdb.org/t/p/w154"
    static let IMAGE_MEDIUM_RESOLUTION_BASE_PATH = "https://image.tmdb.org/t/p/w342/"
    static let IMAGE_HIGH_RESOLUTION_BASE_PATH = "https://image.tmdb.org/t/p/w780/"
    static let MOVIE_DETAILS_PATH = "/movie/"
    static let MOVIE_CREDITS_PATH = "/credits"

    static let POPULAR_MOVIES_URL = MOVIES_DB_BASE_PATH + POPULAR_MOVIES_PATH
    static let UPCOMING_MOVIES_URL = MOVIES_DB_BASE_PATH + UPCOMING_MOVIES_PATH
    static let MOVIE_DETAILS_URL = MOVIES_DB_BASE_PATH + MOVIE_DETAILS_PATH

    // MARK: Parameters
    static let KEY = "api_key"
    static let PAGE = "page"

    // MARK: Headers
    static let MOVIES_DB_HEADER: HTTPHeaders = [
        "Content-Type": "application/json;charset=utf-8"
    ]
}
