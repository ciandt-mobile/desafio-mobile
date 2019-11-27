//
//  GalleryMovies.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 24/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

struct GalleryMovies: Decodable {
    var id: Int?
    var originalTitle: String?
    var posterPath: String?
    var releaseDate: String?
    
    enum CodingKeys: String, CodingKey {
        case id = "id"
        case originalTitle = "original_title"
        case posterPath = "poster_path"
        case releaseDate = "release_date"
    }
}
