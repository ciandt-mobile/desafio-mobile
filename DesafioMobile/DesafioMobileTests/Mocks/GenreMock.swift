//
//  GenreMock.swift
//  DesafioMobileTests
//
//  Created by Eric Winston on 8/31/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import Foundation

@testable import DesafioMobile

class GenreMock{
    var genres = [Genre]()
    
    init() {
        let genre = Genre(id: 28,name: "Action")
        let genre2 = Genre(id: 12, name: "Adventure")
        genres.append(contentsOf: [genre,genre2])
    }
}
