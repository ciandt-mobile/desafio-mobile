//
//  Genere.swift
//  iMovie
//
//  Created by João Tocilo on 01/08/19.
//  Copyright © 2019 Fulltime. All rights reserved.
//

import Foundation

class Genere: NSObject, NSCoding, Decodable {
    
    let id : Int
    let name : String
    
    enum CodingKeys : String, CodingKey {
        case id = "id"
        case name = "name"
    }
    
    init(_ id : Int, _ name : String) {
        self.id = id
        self.name = name
    }
    
    required init(from decoder: Decoder) throws {
        let values = try decoder.container(keyedBy: CodingKeys.self)
        id = try values.decode(Int.self, forKey: .id)
        name = try values.decode(String.self, forKey: .name)
    }
    
    func encode(with aCoder: NSCoder) {
        aCoder.encode(self.id, forKey: CodingKeys.id.rawValue)
        aCoder.encode(self.name, forKey: CodingKeys.name.rawValue)
    }
    
    required convenience init?(coder aDecoder: NSCoder) {
        let id = aDecoder.decodeInteger(forKey: CodingKeys.id.rawValue)
        let name = aDecoder.decodeObject(forKey: CodingKeys.name.rawValue) as! String
        self.init(id, name)
    }
    
}
