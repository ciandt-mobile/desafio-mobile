//
//  MoviesDetails.swift
//  MovieCatalog
//
//  Created by Vitor Antonio Terzariol Terribile on 20/11/19.
//  Copyright © 2019 Vitor Antonio Terzariol Terribile. All rights reserved.
//

import Foundation
import UIKit

struct MovieDetails:Codable {
    var originalTitle:String
    var posterPath:String
    var releaseDate:String
    var overview:String
    var movieId:Int

    enum CodingKeys: String, CodingKey {
        case originalTitle = "original_title"
        case posterPath = "poster_path"
        case releaseDate = "release_date"
        case overview = "overview"
        case movieId = "id"
    }
}
