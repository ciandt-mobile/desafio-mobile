//
//  Movie.swift
//  DesafioMobile
//
//  Created by Eric Winston on 8/27/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

struct Populares: Codable{
    var results: [Movie]?
}


//Movie in the API
class Movie: Codable{
    let poster_path: String?
    var overview: String
    var release_date: String
    var genre_ids: [Int]
    let id: Int
    var title: String
    var backdrop_path: String?
    
    init(id: Int,name: String,genres: [Int], date: String,desc: String,imageP: String, backP: String) {
        self.id = id
        self.title = name
        self.genre_ids = genres
        self.release_date = date
        self.overview = desc
        self.poster_path = imageP
        self.backdrop_path = backP
    }
}


//Extra details nedded later in the app
class DetailedMovie: Codable{
    var runtime: Int?
    var genres: [Genre]?
}
