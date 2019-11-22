//
//  MoviesService.swift
//  MoviesApp
//
//  Created by Luan Audibert on 11/15/19.
//  Copyright © 2019 Luan Audibert. All rights reserved.
//

import Moya
import RxSwift
import RxCocoa
import RxOptional
import Kingfisher
import Foundation

class MoviesService {
    private let disposeBag = DisposeBag()
    private let decoder = JSONDecoder()
    private var provider: MoyaProvider<MovieAPI>?
    
    private var tokenString = "2d746979abba3635175f20f1544ea72f"
    init() {
        decoder.dateDecodingStrategy = .customIso8601
        decoder.keyDecodingStrategy = .convertFromSnakeCase
        let stubClosure = MoyaProvider<MovieAPI>.neverStub
        provider = MoyaProvider<MovieAPI>(stubClosure: stubClosure, plugins: [AccessTokenPlugin { [unowned self] in self.tokenString}
        ])
    }
    
    func getMovies() -> Single<[MovieResult]> {
        return self.provider!.rx.request(.movies)
            .requestPipeline()
            .map(PopularMovieResult.self, using: self.decoder)
            .map{ $0.results }
    }
    
    func getGenreList() -> Single<[Genre]> {
        return self.provider!.rx.request(.movies)
        .requestPipeline()
        .map(GenreResult.self, using: self.decoder)
        .map{ $0.genres }
    }
}

extension PrimitiveSequence where Trait == SingleTrait, Element == Response {
    func requestPipeline() -> Single<Response> {
        return self.catchError { error in
            print("Error on networking pipeline: \(error)")
            return .error(error)
        }.retryWhen{ (errors: Observable<Error>) in
            return errors.enumerated().flatMap { (index, error) -> Observable<Void> in
                guard index < 3 else { return .error(error) }
                let timer = Observable<Int>.timer(
                    TimeInterval(1), scheduler: MainScheduler.instance
                ).map { _ in () }
                return timer
            }
            
        }
    }
}
