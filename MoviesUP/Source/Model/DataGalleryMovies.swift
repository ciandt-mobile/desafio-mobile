//
//  DataGalleryMovies.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 24/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

struct DataGalleryMovies: Decodable {
    var results: [GalleryMovies]
    
    enum CodingKeys: String, CodingKey {
        case results = "results"
    }
}
