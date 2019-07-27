//
//  Dates.swift
//  Movie
//
//  Created by Gabriel Guerrero on 27/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import Foundation

struct Dates : Codable {
    let maximum : String?
    let minimum : String?
    
    enum CodingKeys: String, CodingKey {
        
        case maximum = "maximum"
        case minimum = "minimum"
    }
    
    init(from decoder: Decoder) throws {
        let values = try decoder.container(keyedBy: CodingKeys.self)
        maximum = try values.decodeIfPresent(String.self, forKey: .maximum)
        minimum = try values.decodeIfPresent(String.self, forKey: .minimum)
    }
    
}
