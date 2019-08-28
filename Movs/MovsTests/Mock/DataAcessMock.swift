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
    func getDetail(id: Int, _ fetch: @escaping (Movie?) -> ()) {
        fetch(Movie(id: 0, title: nil, popularity: nil, poster_path: nil, genres: nil, backdrop_path: nil, overview: nil, release_date: nil, runtime: nil, videos: nil))
    }
    
    func getImage(path: String, width: Int, _ fetch: @escaping (UIImage?) -> ()) {

        fetch(nil)
    }
    
    func getMovies(request: Request, page: Int, _ fetch: @escaping ([Movie]) -> ()) {
        fetch([Movie(id: 0, title: nil, popularity: nil, poster_path: nil, genres: nil, backdrop_path: nil, overview: nil, release_date: nil, runtime: nil, videos: nil)])
    }
    
    func getCast(id: String, _ fetch: @escaping ([Cast]) -> ()) {
        fetch([Cast(character: "None", name: "None", profile_path: nil)])
    }    
    
}
