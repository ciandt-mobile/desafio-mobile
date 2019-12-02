//
//  Constants.swift
//  movies
//
//  Created by Arthur Silva on 11/30/19.
//  Copyright © 2019 Arthur Silva. All rights reserved.
//

import Foundation
import UIKit

struct Constants {
    // MARK: Date
    static let API_DATE_FORMAT: String = "yyyy-MM-dd"
    static let APP_DATE_FORMAT: String = "MM/dd/yyyy"

    // MARK: User messages
    static let POPULAR_MOVIES_ERROR_MESSAGE: String = "We had an error getting the popular movies"
    static let FETCHING_NEW_MOVIES_MESSAGE: String = "Fetching new movies..."
    static let FETCHING_NEW_MOVIES_ERROR_MESSAGE: String = "Sorry, I couldn't fetch new movies!"
    static let MOVIE_UNRELEASED: String = "this movie was not released yet"
    static let RELEASE_TOMORROW: String = "Tomorrow is the release!"
    static let DAYS_UNTIL_RELEASE: String = "%d days until the release!"

    // MARK: Numbers
    static let MOVIES_CELL_HEIGHT: CGFloat = 240
    static let MOVIES_CELL_NEW_FETCH_HEIGHT: CGFloat = 44

    // MARK: Movie values
    static let MOVIE_RELEASE_STATUS: String = "Released"
}
