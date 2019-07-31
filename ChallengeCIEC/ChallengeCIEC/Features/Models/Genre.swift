//
//  Genre.swift
//  ChallengeCIEC
//
//  Created by Guilherme C Rosa on 27/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation

struct Genre: Codable {
    
    let id: Int?
    let name: String?
    
    private enum CodingKeys: String, CodingKey {
        case id = "id"
        case name = "name"
    }
}
