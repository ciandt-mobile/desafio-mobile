//
//  GenreResponseModel.swift
//  TheMovieApp
//
//  Created by Wilson Kim on 26/06/19.
//  Copyright © 2019 WilsonKim. All rights reserved.
//

import Foundation

class GenreModel {
    var id:Int
    var name:String
    
    init(_id:Int, _name:String) {
        id = _id
        name = _name
    }
}

class GenreResponseModel: Codable {
    
    var id:Int
    var name:String
    
    func getGenreModel() -> GenreModel {
        return GenreModel(_id: id, _name: name)
    }
}

class GeneralGenreResponseModel: Codable {
    var genres:[GenreResponseModel]
    
    func getGenreModels() -> [GenreModel] {
        var returnModels:[GenreModel] = []
        for genre in genres {
            returnModels.append(genre.getGenreModel())
        }
        return returnModels
    }
}
