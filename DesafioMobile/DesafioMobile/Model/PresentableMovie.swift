//
//  PresentableMovie.swift
//  DesafioMobile
//
//  Created by Eric Winston on 8/27/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//


import UIKit

//MARK: - Protocol with the used parts of the movie in the application
protocol PresentableMovieInterface {
    var id: Int {get}
    var name: String {get}
    var bannerImage: UIImage? {get}
    var description: String {get}
    var genres: [Int] {get}
    var date: String {get}
}


//MARK: - Validates the movie and filter the unused parts
class PresentableMovie: PresentableMovieInterface{
    var id: Int = 0
    var name: String = ""
    var bannerImage: UIImage?
    var description: String = ""
    var genres: [Int] = []
    var date: String = ""
    
    init(movieID: Int,movieTitle: String,movieOverview: String,movieGenres: [Int],movieDate: String,image: UIImage?) {
        
        self.id = movieID
        self.name = movieTitle
        self.description = movieOverview
        self.date = movieDate
        self.genres = movieGenres
        
        
        if let image = image {
            self.bannerImage = image
        }else{
            self.bannerImage = UIImage()
        }
    }
    
    convenience init(){
        self.init(movieID: 0,movieTitle: "",movieOverview: "",movieGenres: [],movieDate: "",image: nil)
    }
}

