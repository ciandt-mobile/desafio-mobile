//
//  APIRoutes.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation

struct APIRoutes {

    // MARK: - Definitions

    enum ImageSize: String {
        case original
        case w780
        case w500
        case h632
    }

    // MARK: - Internal Properties

    static let apiKey: String = "58f995b846d97757fb099d15cdf98a8d"
    static let apiVersion: Int = 3
    static let apiBaseURL: String = "https://api.themoviedb.org/\(APIRoutes.apiVersion)"
}
