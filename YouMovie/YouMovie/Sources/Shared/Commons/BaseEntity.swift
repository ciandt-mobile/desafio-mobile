//
//  BaseEntity.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation
import ObjectMapper

class BaseEntity: NSObject, Mappable {

    // MARK: - Inits

    override init() {
        super.init()
    }

    convenience required init?(map: Map) {
        self.init()
    }

    // MARK: - Internal Methods

    func mapping(map: Map) { }
}
