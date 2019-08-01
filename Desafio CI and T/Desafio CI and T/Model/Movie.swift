//
//  Movie.swift
//  Desafio CI&T
//
//  Created by Mateus Kamoei on 30/07/19.
//  Copyright © 2019 Mateus Kamoei. All rights reserved.
//

import Foundation

class Movie {
    
    private let kIdKey = "id"
    private let kTitleKey = "title"
    private let kReleaseDateKey = "release_date"
    private let kPosterPathKey = "poster_path"
    private let kGenresKey = "genres"
    private let kGenreNameKey = "name"
    private let kRuntimeKey = "runtime"
    private let kOverviewKey = "overview"
    private let kCastKey = "cast"
    private let kBackdropPathKey = "backdrop_path"
    
    let id: Int
    let title: String
    let releaseDate: Date
    let posterPath: String
    var backdropPath: String?
    var genres: [String]?
    var runtime: Int?
    var overview: String?
    var cast: [CastMember]?
    
    init(id: Int, title: String, releaseDate: Date, posterPath: String) {
        self.id = id
        self.title = title
        self.releaseDate = releaseDate
        self.posterPath = posterPath
    }
    
    init(_ dictionary: [String: Any]) {
        self.id = dictionary[kIdKey] as! Int
        self.title = dictionary[kTitleKey] as! String
        self.posterPath = dictionary[kPosterPathKey] as! String
        
        let releaseDateString = dictionary[kReleaseDateKey] as! String
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        dateFormatter.locale = Locale(identifier: "en_US_POSIX") // set locale to reliable US_POSIX
        self.releaseDate = dateFormatter.date(from:releaseDateString)!
        
        if let backdropPath = dictionary[kBackdropPathKey] as? String {
            self.backdropPath = backdropPath
        }
    }
    
    func setMovieDetails(_ dictionary: [String: Any]) {
        if let genres = dictionary[kGenresKey] as? [[String: Any]], genres.count > 0 {
            self.genres = [String]()
            
            for genre in genres {
                self.genres?.append(genre[kGenreNameKey] as! String)
            }
        }
        
        if let runtime = dictionary[kRuntimeKey] as? Int {
            self.runtime = runtime
        }
        
        if let overview = dictionary[kOverviewKey] as? String {
            self.overview = overview
        }
    }
    
    func setCast(_ dictionary: [String: Any]) {
        if let cast = dictionary[kCastKey] as? [[String: Any]], cast.count > 0 {
            self.cast = [CastMember]()
            
            for castMember in cast {
                let castMember = CastMember(castMember)
                self.cast?.append(castMember)
            }
        }
    }
    
}
