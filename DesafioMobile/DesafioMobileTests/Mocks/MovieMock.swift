//
//  MovieMock.swift
//  DesafioMobileTests
//
//  Created by Eric Winston on 8/31/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import Foundation
import UIKit

@testable import DesafioMobile

class MovieMock: PresentableMovieInterface{
    
    var backDropImage: UIImage?
    var genres: [Int]
    var id: Int
    var name: String
    var bannerImage: UIImage?
    var description: String
    var date: String
    
    var hasLoadedImage = false
    
    var genMock = GenreMock()
    
    init() {
        self.id = 429627
        self.name = "Filme"
        self.bannerImage = nil
        self.description = "Nenhuma"
        self.date = "2019878"
        self.genres = []
        
        loadImage(path: "") {_ in }
        date = fixDate(movieDate: "wd7ye8dehd")
    }
    
    func loadImage(path: String, completion: @escaping (UIImage) -> Void) {
        hasLoadedImage = true
    }
    
    func fixDate(movieDate: String) -> String {
        return "2019"
    }
}
