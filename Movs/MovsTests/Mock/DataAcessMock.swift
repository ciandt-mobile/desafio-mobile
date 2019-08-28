//
//  DataAcessMock.swift
//  MovsTests
//
//  Created by Eduardo Pereira on 28/08/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit
@testable import Movs

class DataAcessMock: DataAcess {
    var genres: [Genre] = []
    init() {
        genres = [Genre(id: 0, name: "1"),Genre(id: 0, name: "2")]
    }
    func getImage(path: String, width: Int, _ fetch: @escaping (UIImage?) -> ()) {

        fetch(nil)
    }
    
    func getMovies(request: Request, page: Int, _ fetch: @escaping ([Movie]) -> ()) {
        fetch([Movie(vote_count: nil, id: 0, video: false, vote_average: 0, title: nil, popularity: nil, poster_path: nil, original_language: nil, original_title: nil, genre_ids: nil, backdrop_path: nil, adult: nil, overview: nil, release_date: nil, runtime: nil)])
    }
    
    func getDuration(id: Int, _ fetch: @escaping (Int?) -> ()) {
        fetch(nil)
    }
    
    func getCast(id: String, _ fetch: @escaping ([Cast]) -> ()) {
        fetch([Cast(character: "None", name: "None", profile_path: nil)])
    }
    
    func requestYoutube(id: String) {
    }
    
    
}
