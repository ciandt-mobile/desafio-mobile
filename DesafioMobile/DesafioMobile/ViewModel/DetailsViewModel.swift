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
    func getData(completion: @escaping () -> Void)
}

//MARK: - Init
class DetailsViewModel: DetailsInterface{
    
    weak var loadDelegate: DetailsLoadDelegate?
    var movie: PresentableMovieInterface
    var apiAccess: APIClientInterface
    var movieYear: String = ""
    var runtime: Int = 0 
    var movieGenres: [Genre] = []
    var movieCast: [Actor] = []
    var castImages: [UIImage] = []
    
    init(movie: PresentableMovieInterface, apiAccess: APIClientInterface) {
        self.movie = movie
        self.apiAccess = apiAccess
    }
    
//MARK: - Methods
    func getData(completion: @escaping () -> Void){
        
        movieYear = String(movie.date.prefix(4))
        apiAccess.fetchData(path: ApiPaths.detailMovie(id: movie.id), type: DetailedMovie.self) { [weak self] (fetchedData, error) in
            if error == nil {
                self?.movieGenres = fetchedData.genres ?? []
                self?.runtime = fetchedData.runtime ?? 0
                self?.loadDelegate?.refreshScreen()
            }
        }
        
        apiAccess.fetchData(path: ApiPaths.cast(id: movie.id), type: Cast.self) { [weak self] (cast, error) in
            if error == nil {
                self?.movieCast = cast.cast
                self?.movieCast.forEach({ [weak self] (actor) in 
                    if let imagePath = actor.profile_path {
                        self?.apiAccess.downloadImage(path: imagePath, completion: { (image) in
                            if let img = image {
                                self?.castImages.append(img)
                            }else{
                                self?.castImages.append(UIImage())
                            }
                            
                            self?.loadDelegate?.refreshCastView()
                        })
                    }
                })
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
