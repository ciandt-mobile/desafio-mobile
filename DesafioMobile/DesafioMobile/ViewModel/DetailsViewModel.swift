//
//  DetailViewModel.swift
//  DesafioMobile
//
//  Created by Eric Winston on 8/27/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import UIKit

//MARK: - Interface
protocol DetailsInterface{
    func detailsGenres() -> String
}

//MARK: - Init
class DetailsViewModel: DetailsInterface{
    var movie: PresentableMovieInterface
    var isFavorite: Bool = false
    var displayImage: String = ""
    
    init(movie: PresentableMovieInterface) {
        self.movie = movie
    }
    
    //MARK: - Methods
    
    //Transformes the genres list in a simple string
    func detailsGenres() -> String {
//        var genreSring = ""
//        for genre in movie.genres{
//            genreSring = genreSring + " \(genre.name),"
//        }
//        genreSring.removeLast()
//        return genreSring
        return ""
    }
}
