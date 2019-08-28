//
//  Cast.swift
//  Movs
//
//  Created by Eduardo Pereira on 27/08/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import Foundation

struct CastRequest:Decodable{
    let cast:[Cast]
}
struct Cast:Codable {
    let character:String?
    let name:String?
    let profile_path:String?
}

