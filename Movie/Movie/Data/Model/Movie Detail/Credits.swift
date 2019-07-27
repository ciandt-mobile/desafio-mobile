//
//  Credits.swift
//  Movie
//
//  Created by Gabriel Guerrero on 26/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import Foundation

struct Credits : Codable {
    let cast : [Cast]?
    let crew : [Crew]?
    
    enum CodingKeys: String, CodingKey {
        
        case cast = "cast"
        case crew = "crew"
    }
    
    init(from decoder: Decoder) throws {
        let values = try decoder.container(keyedBy: CodingKeys.self)
        cast = try values.decodeIfPresent([Cast].self, forKey: .cast)
        crew = try values.decodeIfPresent([Crew].self, forKey: .crew)
    }
    
}
