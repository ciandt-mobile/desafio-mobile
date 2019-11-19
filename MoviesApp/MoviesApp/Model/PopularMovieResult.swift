//
//  PopularMovieResult.swift
//  MoviesApp
//
//  Created by Luan Audibert on 11/18/19.
//  Copyright © 2019 Luan Audibert. All rights reserved.
//

import Foundation
import RxCocoa

struct PopularMovieResult: Decodable, Encodable {
    let page: Int
    let totalResults: Int
    let totalPages: Int
    let results: [MovieResult]
}
