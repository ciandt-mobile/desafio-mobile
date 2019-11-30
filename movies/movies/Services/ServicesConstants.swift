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
    static let IMAGE_BASE_PATH = "https://image.tmdb.org/t/p/w154"

    static let POPULAR_MOVIES_URL = MOVIES_DB_BASE_PATH + POPULAR_MOVIES_PATH
    static let UPCOMING_MOVIES_URL = MOVIES_DB_BASE_PATH + UPCOMING_MOVIES_PATH

    // MARK: Parameters
    static let KEY = "api_key"

    // MARK: Headers
    static let MOVIES_DB_HEADER: HTTPHeaders = [
        "Content-Type": "application/json;charset=utf-8"
    ]
}
