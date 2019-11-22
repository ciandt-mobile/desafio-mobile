//
//  MovieDetailsViewModel.swift
//  MoviesApp
//
//  Created by Luan Audibert on 11/18/19.
//  Copyright © 2019 Luan Audibert. All rights reserved.
//

import Foundation
import RxFlow
import RxSwift
import RxCocoa

class MovieDetailsViewModel: Stepper {
    
    var steps = PublishRelay<Step>()
    private let moviesService: MoviesService
    
    private let genresRelay = BehaviorRelay<[Genre]?>(value: nil)
    var genres: Driver<[Genre]?> {
        return genresRelay.asDriver()
    }
    
    private let disposeBag = DisposeBag()
    init(moviesService: MoviesService) {
        self.moviesService = moviesService
    }
    
    func getGenre(movie: MovieResult) {
        self.moviesService.getGenreList().subscribe {
            result in
            switch result {
            case .success(let genres):
                let results = genres.filter { (genre) -> Bool in
                    return movie.genreIds.contains(genre.id)
                }
                self.genresRelay.accept(results)
            case .error:
                self.genresRelay.accept(nil)
                break
            }
        }.disposed(by: disposeBag)
    }
}
