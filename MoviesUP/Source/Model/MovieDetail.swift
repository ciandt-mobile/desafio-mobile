//
//  MovieDetail.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 26/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

struct MovieDetail: Decodable {
    var id: Int?
    var backdropPath: String?
    var originalTitle: String?
    var releaseDate: String?
    var runtimeValue: Int?
    var genres: [Genres] = []
    var overview: String?
    
    init() {}
    
    enum CodingKeys: String, CodingKey {
        case id = "id"
        case backdropPath = "backdrop_path"
        case originalTitle = "original_title"
        case releaseDate = "release_date"
        case runtimeValue = "runtime"
        case genres = "genres"
        case overview = "overview"
    }
}

//MARK: - MovieDetail + Presentation values
extension MovieDetail {
    var categories: String {
        return genres.compactMap { $0.name }.joined(separator: ", ")
    }
    
    var runtime: String {
        guard let runtimeValue = runtimeValue else { return "" }
        return "\(runtimeValue)m"
    }
    
    var timeAndCategories: String {
        return runtime + " | " + categories
    }
}
