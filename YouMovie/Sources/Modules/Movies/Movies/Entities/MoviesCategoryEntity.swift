//
//  MoviesCategoryEntity.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation
import ObjectMapper

class MoviesCategoryEntity: BaseEntity {

    // MARK: - Internal Properties

    var page: Int?
    var totalResults: Int?
    var totalPages: Int?
    var movies: [MovieEntity]?

    // MARK: - Internal Methods

    override func mapping(map: Map) {
        self.page <- map["page"]
        self.totalResults <- map["total_results"]
        self.totalPages <- map["total_pages"]
        self.movies <- map["results"]
    }
}
