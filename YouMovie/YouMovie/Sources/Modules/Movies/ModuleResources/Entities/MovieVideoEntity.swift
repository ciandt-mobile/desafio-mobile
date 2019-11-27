//
//  MovieVideoEntity.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation
import ObjectMapper

class MovieVideoEntity: BaseEntity {

    // MARK: - Definitions

    enum Provider: String {
        case youtube = "YouTube"
    }

    // MARK: - Internal Properties

    var id: String?
    var key: String?
    var name: String?
    var site: Provider?

    // MARK: - Internal Methods

    override func mapping(map: Map) {
        self.id <- map["id"]
        self.key <- map["key"]
        self.name <- map["name"]
        self.site <- map["site"]
    }
}
