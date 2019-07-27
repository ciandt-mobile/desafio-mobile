//
//  Crew.swift
//  Movie
//
//  Created by Gabriel Guerrero on 26/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import Foundation

struct Crew : Codable {
    let credit_id : String?
    let department : String?
    let gender : Int?
    let id : Int?
    let job : String?
    let name : String?
    let profile_path : String?
    
    enum CodingKeys: String, CodingKey {
        
        case credit_id = "credit_id"
        case department = "department"
        case gender = "gender"
        case id = "id"
        case job = "job"
        case name = "name"
        case profile_path = "profile_path"
    }
    
    init(from decoder: Decoder) throws {
        let values = try decoder.container(keyedBy: CodingKeys.self)
        credit_id = try values.decodeIfPresent(String.self, forKey: .credit_id)
        department = try values.decodeIfPresent(String.self, forKey: .department)
        gender = try values.decodeIfPresent(Int.self, forKey: .gender)
        id = try values.decodeIfPresent(Int.self, forKey: .id)
        job = try values.decodeIfPresent(String.self, forKey: .job)
        name = try values.decodeIfPresent(String.self, forKey: .name)
        profile_path = try values.decodeIfPresent(String.self, forKey: .profile_path)
    }
    
}
