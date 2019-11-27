//
//  Genres.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 26/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

struct Genres: Decodable {
    var id: Int?
    var name: String?
    
    init(id: Int?, name: String?) {
        self.id = id
        self.name = name
    }
    
    enum CodingKeys: String, CodingKey {
        case id = "id"
        case name = "name"
    }
}
