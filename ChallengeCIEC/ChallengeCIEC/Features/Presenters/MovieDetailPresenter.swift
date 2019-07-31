//
//  MovieDetailPresenter.swift
//  ChallengeCIEC
//
//  Created by Guilherme C Rosa on 27/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation

protocol MovieDetailPresenterProtocol {
    func didUpdateBuilder(builder: MovieDetailTableBuilder)
}

class MovieDetailPresenter {
    
    var delegate: MovieDetailPresenterProtocol?
    
    private(set) var builder = MovieDetailTableBuilder(state: .Loading, error: "") {
        didSet { self.delegate?.didUpdateBuilder(builder: self.builder) }
    }
    
    func fetchMovieDetail(movie: Movie) {
        self.builder = MovieDetailTableBuilder(state: .Loading, error: "")
        
        let service = TheMovieDBService()
        service.getMovieDetail(movie: movie) { (movie, error) in
            if let err = error {
                self.builder = MovieDetailTableBuilder(state: .Error, error: err.localizedDescription)
            } else {
                if let m = movie {
                    self.fetchMovieCredits(movie: m)
                } else {
                    self.builder = MovieDetailTableBuilder(state: .Error, error: "Parsing Error")
                }
            }
        }
    }
    
    func fetchMovieCredits(movie: MovieDetail) {
        self.builder = MovieDetailTableBuilder(state: .Loading, error: "")
        
        let service = TheMovieDBService()
        service.getMovieCredits(movie: movie) { (credits, error) in
            if let _ = error {
                self.builder = MovieDetailTableBuilder(state: .Success, movie: movie, credits: nil)
            } else {
                self.builder = MovieDetailTableBuilder(state: .Success, movie: movie, credits: credits)
            }
        }
    }
}
