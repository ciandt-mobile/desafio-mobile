//
//  GenreResult.swift
//  MoviesApp
//
//  Created by Luan Audibert on 11/21/19.
//  Copyright © 2019 Luan Audibert. All rights reserved.
//

import Foundation

struct GenreResult: Decodable {
    let genres: [Genre]
}

struct Genre: Decodable {
    let id: Int
    let name: String
}
