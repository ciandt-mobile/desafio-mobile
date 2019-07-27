//
//  BelongsToCollection.swift
//  Movie
//
//  Created by Gabriel Guerrero on 27/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import Foundation

struct Belongs_to_collection : Codable {
    let id : Int?
    let name : String?
    let poster_path : String?
    let backdrop_path : String?
    
    enum CodingKeys: String, CodingKey {
        case id = "id"
        case name = "name"
        case poster_path = "poster_path"
        case backdrop_path = "backdrop_path"
    }
    
    init(from decoder: Decoder) throws {
        let values = try decoder.container(keyedBy: CodingKeys.self)
        id = try values.decodeIfPresent(Int.self, forKey: .id)
        name = try values.decodeIfPresent(String.self, forKey: .name)
        poster_path = try values.decodeIfPresent(String.self, forKey: .poster_path)
        backdrop_path = try values.decodeIfPresent(String.self, forKey: .backdrop_path)
    }
    
}
