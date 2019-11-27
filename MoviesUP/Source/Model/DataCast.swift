//
//  DataCast.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 27/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import Foundation

struct DataCast: Decodable {
    var cast: [Cast]
    
    enum CodingKeys: String, CodingKey {
        case cast
    }
}
