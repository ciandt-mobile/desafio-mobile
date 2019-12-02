//
//  GenericMoviesViewModel.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 30/11/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import Foundation

protocol GenericMoviesViewModelDelegate: class {
    func updateView()
}

struct MoviePagination {
    var movieCurrentPage: Int?
    var movieTotalPages: Int?
}

class GenericMoviesViewModel {

    private var movieResult: MovieResult?
    private var movies: [Movie]?
    private var pagination = MoviePagination()

    weak var delegate: GenericMoviesViewModelDelegate?

    enum MovieTableViewCellCategory: Int {
        case nowPlaying, popular, upComing
    }

    private func updateView() {
        self.delegate?.updateView()
    }

    func getMovies() -> [Movie]? {
        return movies
    }
    
    func getMovie(_ index: Int) -> Movie? {
        return movies?[index]
    }

    func getMovieResult() -> MovieResult? {
        return movieResult
    }
    
    func getTotalPages() -> Int? {
        return movieResult?.totalPages
    }
    
    func getMoviesCount() -> Int? {
        return movies?.count
    }

    func setMovies(_ movies: [Movie]) {
        self.movies = movies
        updateView()
    }

    func updateMovies(_ movies: [Movie]) {
        self.movies?.append(contentsOf: movies)
        updateView()
    }

    func setMovieResult(_ movieResult: MovieResult?) {
        self.movieResult = movieResult
        updateView()
    }

    func setPagination(_ data: MovieResult) {
        pagination.movieCurrentPage = data.page ?? 1
        pagination.movieTotalPages = data.totalPages ?? 1
    }

    func getPagination() -> MoviePagination {
        return pagination
    }

}
