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
    func getData()
}

//MARK: - Init
class DetailsViewModel: DetailsInterface{
    
    weak var loadDelegate: DetailsLoadDelegate?
    var movie: PresentableMovieInterface
    var apiAccess: APIClientInterface
    var movieYear: String = ""
    var runtime: Int = 0 
    var movieGenres: [Genre] = []
    
    init(movie: PresentableMovieInterface, apiAccess: APIClientInterface) {
        self.movie = movie
        self.apiAccess = apiAccess
    }
    
    func getData(){
        movieYear = String(movie.date.prefix(4))
        apiAccess.fetchData(path: ApiPaths.detailMovie(id: movie.id), type: DetailedMovie.self) { [weak self](fetchedData, error) in
            if error == nil {
                self?.movieGenres = fetchedData.genres ?? []
                self?.runtime = fetchedData.runtime ?? 0
                self?.loadDelegate?.refreshScreen()
            }
        }
    }
    
    
    //Transformes the genres list in a simple string
    func detailsGenres() -> String {
        var genreSring = ""
        for genre in movieGenres{
            genreSring = genreSring + " \(genre.name),"
        }
        
        if genreSring == ""{
            return ""
        }else{
            genreSring.removeLast()
            return genreSring
        }
    }
}
