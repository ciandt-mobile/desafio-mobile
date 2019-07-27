//
//  MoviesList.swift
//  Movie
//
//  Created by Gabriel Guerrero on 26/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import Foundation

struct UpComingMovies : Codable {
    let results : [Movies]?
    let page : Int?
    let total_results : Int?
    let dates : Dates?
    let total_pages : Int?
    
    enum CodingKeys: String, CodingKey {
        
        case results = "results"
        case page = "page"
        case total_results = "total_results"
        case dates = "dates"
        case total_pages = "total_pages"
    }
    
    init(from decoder: Decoder) throws {
        let values = try decoder.container(keyedBy: CodingKeys.self)
        results = try values.decodeIfPresent([Movies].self, forKey: .results)
        page = try values.decodeIfPresent(Int.self, forKey: .page)
        total_results = try values.decodeIfPresent(Int.self, forKey: .total_results)
        dates = try values.decodeIfPresent(Dates.self, forKey: .dates)
        total_pages = try values.decodeIfPresent(Int.self, forKey: .total_pages)
    }
    
}
