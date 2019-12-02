//
//  Cast.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 30/11/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import Foundation

struct DataCast: Decodable {
    var cast: [Cast]
}

struct Cast: Decodable {
    var id: Int?
    var character: String?
    var creditId: String?
    var name: String?
    var profilePath: String?
}
