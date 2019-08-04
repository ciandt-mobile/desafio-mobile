//
//  Cast.swift
//  iMovie
//
//  Created by João Tocilo on 02/08/19.
//  Copyright © 2019 Fulltime. All rights reserved.
//

import Foundation

class Cast: NSObject, NSCoding, Decodable {
    
    let id : Int
    let castId : Int
    let character : String
    let creditId : String
    let gender : Int
    let name : String
    let order : Int
    let profilePath : String?
    
    init(_ id : Int, _ castId : Int, _ character : String, _ creditId : String, _ gender : Int, _ name : String, _ order : Int, _ profilePath : String) {
        self.id = id
        self.castId = castId
        self.character = character
        self.creditId = creditId
        self.gender = gender
        self.name = name
        self.order = order
        self.profilePath = profilePath
    }
    
    enum CodingKeys : String, CodingKey {
        case id = "id"
        case castId = "cast_id"
        case character = "character"
        case creditId = "credit_id"
        case gender = "gender"
        case name = "name"
        case order = "order"
        case profilePath = "profile_path"
    }
    
    func encode(with aCoder: NSCoder) {
        aCoder.encode(self.id, forKey: CodingKeys.id.rawValue)
        aCoder.encode(self.castId, forKey: CodingKeys.castId.rawValue)
        aCoder.encode(self.character, forKey: CodingKeys.character.rawValue)
        aCoder.encode(self.creditId, forKey: CodingKeys.creditId.rawValue)
        aCoder.encode(self.gender, forKey: CodingKeys.gender.rawValue)
        aCoder.encode(self.name, forKey: CodingKeys.name.rawValue)
        aCoder.encode(self.order, forKey: CodingKeys.order.rawValue)
        aCoder.encode(self.profilePath, forKey: CodingKeys.profilePath.rawValue)
    }
    
    required convenience init?(coder aDecoder: NSCoder) {
        let id = aDecoder.decodeInteger(forKey: CodingKeys.id.rawValue)
        let castId = aDecoder.decodeInteger(forKey: CodingKeys.castId.rawValue)
        let character = aDecoder.decodeObject(forKey: CodingKeys.character.rawValue) as! String
        let creditId = aDecoder.decodeObject(forKey: CodingKeys.creditId.rawValue) as! String
        let gender = aDecoder.decodeInteger(forKey: CodingKeys.gender.rawValue)
        let name = aDecoder.decodeObject(forKey: CodingKeys.name.rawValue) as! String
        let order = aDecoder.decodeInteger(forKey: CodingKeys.order.rawValue)
        let profilePath = aDecoder.decodeObject(forKey: CodingKeys.profilePath.rawValue) as! String
        self.init(id, castId, character, creditId, gender, name, order, profilePath)
    }
    
}
