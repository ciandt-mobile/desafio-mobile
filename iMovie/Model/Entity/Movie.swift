//
//  Movie.swift
//  iMovie
//
//  Created by João Tocilo on 31/07/19.
//  Copyright © 2019 Fulltime. All rights reserved.
//

import Foundation

class Movie: NSObject, NSCoding, Decodable {
    
    let id : Int
    let voteCount : Int
    let video : Bool
    let voteAverage : Double
    let title : String
    let popularity : Double
    let posterPath : String
    let originalLanguage : String
    let originalTitle : String
    let genreIds : [Int]
    let backdropPath : String?
    let adult : Bool
    let overview : String
    let releaseDate : String
    var genres : [Genere]?
    var cast : [Cast]?
    var runtime : Int?
    
    enum CodingKeys : String, CodingKey {
        case id = "id"
        case voteCount = "vote_count"
        case video = "video"
        case voteAverage = "vote_average"
        case title = "title"
        case popularity = "popularity"
        case posterPath = "poster_path"
        case originalLanguage = "original_language"
        case originalTitle = "original_title"
        case genreIds = "genre_ids"
        case backdropPath = "backdrop_path"
        case adult = "adult"
        case overview = "overview"
        case releaseDate = "release_date"
        case genres = "genres"
        case cast = "cast"
        case runtime = "runtime"
    }
    
    init(_ id : Int, _ voteCount : Int, _ video : Bool, _ voteAverage : Double, _ title : String, _ popularity : Double, _ posterPath : String, _ originalLanguage : String, _ originalTitle : String, _ genreIds : [Int], _ backdropPath : String?, _ adult : Bool, _ overview : String, _ releaseDate : String, _ genres : [Genere]?, _ cast : [Cast]?, _ runtime : Int?) {
        self.id = id
        self.voteCount = voteCount
        self.video = video
        self.voteAverage = voteAverage
        self.title = title
        self.popularity = popularity
        self.posterPath = posterPath
        self.originalLanguage = originalLanguage
        self.originalTitle = originalTitle
        self.genreIds = genreIds
        self.backdropPath = backdropPath
        self.adult = adult
        self.overview = overview
        self.releaseDate = releaseDate
        self.genres = genres
        self.cast = cast
        self.runtime = runtime
    }
    
    func encode(with aCoder: NSCoder) {
        aCoder.encode(self.id, forKey: CodingKeys.id.rawValue)
        aCoder.encode(self.voteCount, forKey: CodingKeys.voteCount.rawValue)
        aCoder.encode(self.video, forKey: CodingKeys.video.rawValue)
        aCoder.encode(self.voteAverage, forKey: CodingKeys.voteAverage.rawValue)
        aCoder.encode(self.title, forKey: CodingKeys.title.rawValue)
        aCoder.encode(self.popularity, forKey: CodingKeys.popularity.rawValue)
        aCoder.encode(self.posterPath, forKey: CodingKeys.posterPath.rawValue)
        aCoder.encode(self.originalLanguage, forKey: CodingKeys.originalLanguage.rawValue)
        aCoder.encode(self.originalTitle, forKey: CodingKeys.originalTitle.rawValue)
        aCoder.encode(self.genreIds, forKey: CodingKeys.genreIds.rawValue)
        aCoder.encode(self.backdropPath, forKey: CodingKeys.backdropPath.rawValue)
        aCoder.encode(self.adult, forKey: CodingKeys.adult.rawValue)
        aCoder.encode(self.overview, forKey: CodingKeys.overview.rawValue)
        aCoder.encode(self.releaseDate, forKey: CodingKeys.releaseDate.rawValue)
        aCoder.encode(self.genres, forKey: CodingKeys.genres.rawValue)
        aCoder.encode(self.cast, forKey: CodingKeys.cast.rawValue)
        aCoder.encode(self.runtime, forKey: CodingKeys.runtime.rawValue)
    }
    
    required convenience init?(coder aDecoder: NSCoder) {
        let id = aDecoder.decodeInteger(forKey: CodingKeys.id.rawValue)
        let voteCount = aDecoder.decodeInteger(forKey: CodingKeys.voteCount.rawValue)
        let video = aDecoder.decodeBool(forKey: CodingKeys.video.rawValue)
        let voteAverage = aDecoder.decodeDouble(forKey: CodingKeys.voteAverage.rawValue)
        let title = aDecoder.decodeObject(forKey: CodingKeys.title.rawValue) as! String
        let popularity = aDecoder.decodeDouble(forKey: CodingKeys.popularity.rawValue)
        let posterPath = aDecoder.decodeObject(forKey: CodingKeys.posterPath.rawValue) as! String
        let originalLanguage = aDecoder.decodeObject(forKey: CodingKeys.originalLanguage.rawValue) as! String
        let originalTitle = aDecoder.decodeObject(forKey: CodingKeys.originalTitle.rawValue) as! String
        let genreIds = aDecoder.decodeObject(forKey: CodingKeys.genreIds.rawValue) as! [Int]
        let backdropPath = aDecoder.decodeObject(forKey: CodingKeys.backdropPath.rawValue) as? String
        let adult = aDecoder.decodeBool(forKey: CodingKeys.adult.rawValue)
        let overview = aDecoder.decodeObject(forKey: CodingKeys.overview.rawValue) as! String
        let releaseDate = aDecoder.decodeObject(forKey: CodingKeys.releaseDate.rawValue) as! String
        let genres = aDecoder.decodeObject(forKey: CodingKeys.genres.rawValue) as? [Genere]
        let cast = aDecoder.decodeObject(forKey: CodingKeys.cast.rawValue) as? [Cast]
        let runtime = aDecoder.decodeInteger(forKey: CodingKeys.runtime.rawValue)
        
        self.init(id, voteCount, video, voteAverage, title, popularity, posterPath, originalLanguage, originalTitle, genreIds, backdropPath, adult, overview, releaseDate, genres, cast, runtime)
    }
    
}
