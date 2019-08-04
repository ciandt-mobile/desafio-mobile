//
//  Credits.swift
//  iMovie
//
//  Created by João Tocilo on 03/08/19.
//  Copyright © 2019 Fulltime. All rights reserved.
//

import Foundation

class Credits: NSObject, NSCoding, Decodable {
    
    let cast : [Cast]
    
    init(_ cast : [Cast]) {
        self.cast = cast
    }
    
    enum CodingKeys : String, CodingKey {
        case cast = "cast"
    }
    
    func encode(with aCoder: NSCoder) {
        aCoder.encode(self.cast, forKey: CodingKeys.cast.rawValue)
    }
    
    required convenience init?(coder aDecoder: NSCoder) {
        let cast = aDecoder.decodeObject(forKey: CodingKeys.cast.rawValue) as! [Cast]
        self.init(cast)
    }
    
}
