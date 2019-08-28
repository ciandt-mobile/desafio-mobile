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

struct Movie:Decodable {
    //parameters needed
    let id:Int
    let title:String?
    let popularity:Float?
    let poster_path:String?
    let genres:[Genre]?
    let backdrop_path:String?
    let overview:String?
    let release_date:String?
    let runtime:Int?
    let videos:APIRequest<Video>?
}
