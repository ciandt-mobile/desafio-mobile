//
//  CastMember.swift
//  Desafio CI&T
//
//  Created by Mateus Kamoei on 30/07/19.
//  Copyright © 2019 Mateus Kamoei. All rights reserved.
//

import Foundation

class CastMember {
    
    let kNameKey = "name"
    let kProfilePathKey = "profile_path"
    
    let name: String
    var profilePath: String?
    
    init(name: String, profilePath: String) {
        self.name = name
        self.profilePath = profilePath
    }
    
    init(_ dictionary: [String: Any]) {
        self.name = dictionary[kNameKey] as! String
        if let profilePath = dictionary[kProfilePathKey] as? String {
            self.profilePath = profilePath
        }
    }
    
}
