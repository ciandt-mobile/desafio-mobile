//
//  Cast.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 27/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import Foundation

struct Cast: Decodable {
    var id: Int?
    var character: String?
    var creditId: String?
    var name: String?
    var profilePath: String?
    
    enum CodingKeys: String, CodingKey {
        case id
        case creditId = "credit_id"
        case character = "character"
        case name = "name"
        case profilePath = "profile_path"
    }
}
