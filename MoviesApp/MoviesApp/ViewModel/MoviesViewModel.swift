//
//  MoviesViewModel.swift
//  MoviesApp
//
//  Created by Luan Audibert on 11/14/19.
//  Copyright © 2019 Luan Audibert. All rights reserved.
//

import Foundation
import RxSwift
import RxCocoa
import RxFlow

class MoviesViewModel: Stepper {
    let disposeBag = DisposeBag()
    var steps = PublishRelay<Step>()
    private let moviesService: MoviesService
    private let moviesRelay = BehaviorRelay<[MovieResult]>(value: [])
    
    var movies: Driver<[MovieResult]> {
        return moviesRelay.asDriver()
    }
    init(moviesService: MoviesService) {
        self.moviesService = moviesService
    }
    
    func getMovies () {
        self.moviesService.getMovies().subscribe { result in
            switch result {
            case .success(let movies):
                self.moviesRelay.accept(movies)
            case .error:
                self.moviesRelay.accept([])
            }
        }.disposed(by: self.disposeBag)
    }
    
    func userSelectedMovie(movie: MovieResult) {
        self.steps.accept(MoviesState.details(movie: movie))
    }
}
