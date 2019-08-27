//
//  Movie.swift
//  Movs
//
//  Created by Eduardo Pereira on 26/08/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import Foundation

struct APIRequest<T:Decodable>:Decodable{
    let results:[T]
}
struct CastRequest:Decodable{
    let cast:[Cast]
}
struct Cast:Codable {
    let character:String?
    let name:String?
    let profile_path:String?
}


struct Movie:Codable {
    //parameters needed
    let vote_count:Int?
    let id:Int
    let video:Bool
    let vote_average:Float
    let title:String?
    let popularity:Float?
    let poster_path:String?
    let original_language:String?
    let original_title:String?
    let genre_ids:[Int]?
    let backdrop_path:String?
    let adult:Bool?
    let overview:String?
    let release_date:String?
    let runtime:Int?
    
}
