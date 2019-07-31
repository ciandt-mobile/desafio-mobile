//
//  MoviesPresenter.swift
//  ChallengeCIEC
//
//  Created by Guilherme Camilo Rosa on 25/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation

protocol MoviesPresenterDelegate {
    func didChangeCategory(category: MovieCategory)
    func didUpdateCollectBuilder(builder: MoviesCollectionBuilder)
}

class MoviesPresenter {
    
    var delegate: MoviesPresenterDelegate?
    
    private(set) var category = MovieCategory.Upcoming
    private(set) var builder = MoviesCollectionBuilder(state: .Loading, movies: nil) {
        didSet { self.delegate?.didUpdateCollectBuilder(builder: self.builder) }
    }
    
    func changeCategory(sender: Int) {
        switch sender {
        case 0:
            self.category = .Upcoming
        case 1:
            self.category = .Popular
        default:
            break
        }
        
        self.delegate?.didChangeCategory(category: self.category)
    }
    
    func fetchMovies() {
        self.builder = MoviesCollectionBuilder(state: .Loading, movies: nil)
        
        let service = TheMovieDBService()
        service.getMovies(category: self.category) { (list, error) in
            if let err = error {
                self.builder = MoviesCollectionBuilder(state: .Error, error: err.localizedDescription)
            } else {
                if let movies = list {
                    if movies.isEmpty {
                        self.builder = MoviesCollectionBuilder(state: .Empty, error: "Empty List")
                    } else {
                        self.builder = MoviesCollectionBuilder(state: .Success, movies: movies)
                    }
                } else {
                    self.builder = MoviesCollectionBuilder(state: .Error, error: "Parsing Error")
                }
            }
        }
    }
}
