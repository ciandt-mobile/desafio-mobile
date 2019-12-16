//
//  Detail.swift
//  moviesTest
//
//  Created by Pedro  Apolloni on 15/12/19.
//  Copyright © 2019 Henrique Augusto. All rights reserved.
//

import Foundation

struct Detail: Codable{
    let adult: Bool
    let backdrop_path: String
    let belongs_to_collection: BelongsToCollection?
    let budget: Int
    let genres: [Genres]
    let homepage: String?
    let id: Int
    let imdb_id: String
    let original_language: String
    let original_title: String
    let overview: String
    let popularity: Double
    let poster_path: String
    let production_companies: [ProductionCompanies]
    let production_countries: [ProductionCountries]
    let release_date: String
    let revenue: Int
    let runtime: Int
    let spoken_languages: [SpokenLanguages]
    let status: String
    let tagline: String
    let title: String
    let video: Bool
    let vote_average: Double
    let vote_count: Int
    
    enum CodinKeys: String, CodingKey {
        case adult = "adult"
        case budget = "budget"
        case genres = "genres"
        case homepage = "homepage"
        case id = "id"
        case overview = "overview"
        case popularity = "popularity"
        case revenue = "revenue"
        case runtime = "runtime"
        case status = "status"
        case tagline = "tagline"
        case title = "title"
        case video = "video"
    }
}

struct BelongsToCollection: Codable {
    let id: Int
    let name: String
    let poster_path: String
    let backdrop_path: String
    
    enum CodinKeys: String, CodingKey {
        case id = "id"
        case name = "name"
    }
}

struct Genres: Codable {
    let id: Int
    let name: String
    
    enum CodinKeys: String, CodingKey {
        case id = "id"
        case name = "name"
    }
}

struct ProductionCompanies: Codable {
    let id: Int
    let logo_path: String?
    let name: String
    let origin_country: String
    
    enum CodinKeys: String, CodingKey {
        case id = "id"
        case name = "name"
    }
}

struct ProductionCountries: Codable {
    let iso_3166_1: String
    let name: String
    
    enum CodinKeys: String, CodingKey {
        case name = "name"
    }
}

struct SpokenLanguages: Codable {
    let iso_639_1: String
    let name: String
    
    enum CodinKeys: String, CodingKey {
        case name = "name"
    }
}
