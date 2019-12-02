//
//  Constants.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 30/11/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import UIKit

struct Constants {
    static let apiURLBase                       = "https://api.themoviedb.org/3/"
    static let nowPlayingEndPoint               = "movie/now_playing?"
    static let popularEndPoint                  = "movie/popular?"
    static let upComingEndPoint                 = "movie/upcoming?"
    static let searchEndPoint                   = "search/movie?"
    static let genreEndPoint                    = "genre/list?"
    static let castEndPoint                     = "movie/movieid/credits?"
    static let apiToken                         = "1f54bd990f1cdfb230adb312546d765d"
    static let apiURLImageBase                  = "https://image.tmdb.org/t/p/w500"
    static let defaultImage                     = "popcorn"
    static let detaultImageSize512              = "popcorn@512x"
    // Constant for the list pagination
    static let leadingScreensForBatching: CGFloat = 2.0

    struct StringIdentifier {
        struct CollectionViewCell {
            static let castCollectionViewCell               = "CastCollectionViewCell"
            static let movieCollectionViewCell              = "MovieCollectionViewCell"
            static let cell                                 = "Cell"
        }

        struct ViewController {
            static let movieDetailsViewController           = "MovieDetailsViewController"
        }
    }
}
