//
//  Cast.swift
//  DesafioMobile
//
//  Created by Eric Winston on 8/29/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//



class Actor: Codable{
    var name: String?
    var profile_path: String?
    var character: String?
}

class Cast: Codable {
    var cast: [Actor]
}

