//
//  MovieDetail.swift
//  MobileChallengeCIET
//
//  Created by Guilherme C Rosa on 22/07/19.
//  Copyright © 2019 Guilherme C Rosa. All rights reserved.
//

import Foundation

struct MovieDetail: Codable {
    let id: Int?
    let title: String?
    let overview: String?
    let popularity: Double?
    let posterPath: String?
    let homepage: String?
    
    private enum CodingKeys: String, CodingKey {
        case id = "id"
        case title = "title"
        case overview = "overview"
        case popularity = "popularity"
        case posterPath = "poster_path"
        case homepage = "homepage"
    }
}
