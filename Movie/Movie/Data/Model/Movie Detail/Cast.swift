//
//  Cast.swift
//  Movie
//
//  Created by Gabriel Guerrero on 26/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import Foundation

struct Cast : Codable {
    let cast_id : Int?
    let character : String?
    let credit_id : String?
    let gender : Int?
    let id : Int?
    let name : String?
    let order : Int?
    let profile_path : String?
    
    enum CodingKeys: String, CodingKey {
        
        case cast_id = "cast_id"
        case character = "character"
        case credit_id = "credit_id"
        case gender = "gender"
        case id = "id"
        case name = "name"
        case order = "order"
        case profile_path = "profile_path"
    }
    
    init(from decoder: Decoder) throws {
        let values = try decoder.container(keyedBy: CodingKeys.self)
        cast_id = try values.decodeIfPresent(Int.self, forKey: .cast_id)
        character = try values.decodeIfPresent(String.self, forKey: .character)
        credit_id = try values.decodeIfPresent(String.self, forKey: .credit_id)
        gender = try values.decodeIfPresent(Int.self, forKey: .gender)
        id = try values.decodeIfPresent(Int.self, forKey: .id)
        name = try values.decodeIfPresent(String.self, forKey: .name)
        order = try values.decodeIfPresent(Int.self, forKey: .order)
        profile_path = try values.decodeIfPresent(String.self, forKey: .profile_path)
    }
    
}
