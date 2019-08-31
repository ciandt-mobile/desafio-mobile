//
//  PopularViewModel.swift
//  DesafioMobile
//
//  Created by Eric Winston on 8/27/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import UIKit


//MARK: - Interface
protocol PopularInterface: class{
    var pageCount: Int {get set}
    var movies: [PresentableMovieInterface] {get set}
    func loadMovies(type: String)
    func resetMovies()
}


//MARK: - Init
class PopularViewModel{
    public var pageCount = 0
    weak var refreshDelegate: PopularGridViewModelDelegate?
    var apiAccess: APIClientInterface
    var atualType: String = "popular"
    
    
    var movies: [PresentableMovieInterface] = []
    
    init(apiAccess: APIClientInterface) {
        self.apiAccess = apiAccess
        loadMovies(type: atualType)
    }
}


//MARK: - Methods
extension PopularViewModel: PopularInterface{
    
    //Loads the movies from the API
    func loadMovies(type: String){
        pageCount += 1
        
        apiAccess.fetchData(path: ApiPaths.movies(page: pageCount,type: type), type: Populares.self) { [weak self] (fetchedMovies,error) in
            guard let checkMovies = fetchedMovies.results else {fatalError("Error fetching the movies form the API")}
            if error == nil {
                checkMovies.forEach({ [weak self] (movie) in
                        guard let self = self else {return}
                        self.movies.append(PresentableMovie(movie: movie, apiAccess: self.apiAccess, completion: {
                        self.refreshDelegate?.refreshMovieData()
                    }))
                })
            }else{
                self?.movies = []
            }
        }
    }
    
    //Reset the movie array
    func resetMovies(){
        pageCount = 0
        movies.removeAll()
        
        if atualType == "popular" {
            atualType = "upcoming"
        }else{
            atualType = "popular"
        }
    }
}

