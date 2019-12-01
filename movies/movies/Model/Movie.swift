//
//  Movie.swift
//  movies
//
//  Created by Arthur Silva on 11/29/19.
//  Copyright © 2019 Arthur Silva. All rights reserved.
//

import Foundation

struct Movie: Codable {
    let id: Int
    let poster_path: String?
    let backdrop_path: String?
    let original_title: String
    let title: String
    let vote_average: Double
    let overview: String
    let release_date: String

    var releaseDateComponents: DateComponents? {
        get {
            let dateFormatter = DateFormatter()
            dateFormatter.dateFormat = Constants.API_DATE_FORMAT
            guard let releaseDate = dateFormatter.date(from: release_date) else {
                return nil
            }

            let calendar = Calendar.current
            return calendar.dateComponents([.year, .month, .day], from: releaseDate)
        }
    }
}
