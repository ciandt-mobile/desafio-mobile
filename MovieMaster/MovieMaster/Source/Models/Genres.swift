//
//  Genres.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 30/11/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import Foundation

struct Genres: Decodable {
    let genres: [Genre]
}

struct Genre : Decodable {
    var id : Int?
    var name : String?
}
