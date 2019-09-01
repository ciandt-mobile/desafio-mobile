//
//  CastMock.swift
//  DesafioMobileTests
//
//  Created by Eric Winston on 8/31/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import Foundation

@testable import DesafioMobile

class CastMock{
    var actors = [Actor]()
    
    init() {
        let actor1 = Actor(name: "Ruiz", imagePath: "", char: "Batatinha")
        let actor2 = Actor(name: "Roberto", imagePath: "", char: "Manda-Chuva")
        actors.append(contentsOf: [actor1,actor2])
    }
}

