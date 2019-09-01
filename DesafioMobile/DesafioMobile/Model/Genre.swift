//
//  Genre.swift
//  DesafioMobile
//
//  Created by Eric Winston on 8/28/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//


class Genre: Codable{
    var id: Int
    var name: String
    
    init(id: Int,name: String) {
        self.id = id
        self.name = name
    }
}

