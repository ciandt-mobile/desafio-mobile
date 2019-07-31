//
//  MovieCredits.swift
//  ChallengeCIEC
//
//  Created by Guilherme C Rosa on 28/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation

struct MovieCredits: Codable {
    
    let id: Int?
    let cast: [Cast]?
    
    private enum CodingKeys: String, CodingKey {
        case id = "id"
        case cast = "cast"
    }
}
