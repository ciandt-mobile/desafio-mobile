//
//  Upcoming.swift
//  moviesTest
//
//  Created by Pedro  Apolloni on 14/12/19.
//  Copyright © 2019 Henrique Augusto. All rights reserved.
//

import Foundation

struct Upcoming: Codable {
    
    let page: Int
    let results: [Movie]
    let total_results: Int
    let total_pages: Int
    
    enum CodinKeys: String, CodingKey {
        case results = "results"
    }
    
}

struct Movie: Codable {
    
    let popularity: Double
    let vote_count: Double
    let video: Bool
    let poster_path: String?
    let id: Int
    let adult: Bool
    let backdrop_path: String?
    let original_language: String
    let original_title: String
    let genre_ids: [Int]
    let title: String
    let vote_average: Double
    let overview: String
    let release_date: String
    
    enum CodinKeys: String, CodingKey {
        case adult = "adult"
        case id = "id"
        case overview = "overview"
        case popularity = "popularity"
        case title = "title"
        case video = "video"
        case vote_count = "vote_count"
        case poster_path = "poster_path"
        case backdrop_path = "backdrop_path"
    }
}
