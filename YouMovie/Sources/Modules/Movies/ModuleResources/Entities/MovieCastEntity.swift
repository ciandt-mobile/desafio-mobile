//
//  MovieCastEntity.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation
import ObjectMapper

class MovieCastEntity: BaseEntity {

    // MARK: - Internal Properties

    var id: Int?
    var creditID: String?
    var name: String?
    var character: String?
    var profilePathURL: String?

    // MARK: - Internal Methods

    override func mapping(map: Map) {
        self.id <- map["id"]
        self.creditID <- map["credit_id"]
        self.name <- map["name"]
        self.character <- map["character"]
        self.profilePathURL <- map["profile_path"]
    }
}
