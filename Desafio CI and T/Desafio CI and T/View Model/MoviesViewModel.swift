//
//  MoviesViewModel.swift
//  Desafio CI&T
//
//  Created by Mateus Kamoei on 31/07/19.
//  Copyright © 2019 Mateus Kamoei. All rights reserved.
//

import Foundation
import UIKit

protocol MoviesViewModelDelegate {
    func didFinishGettingPopularMovies(_ viewModel: MoviesViewModel, dictionary: [String: Any])
    func didFailGettingPopularMovies(_ viewModel: MoviesViewModel, error: Error?)
}

class MoviesViewModel {
    
    private let kResultsKey = "results"
    private let delegate: MoviesViewModelDelegate
    
    private var network: Network!
    private var movies: [Movie]!
    private var allMovies: [Movie]!
    private var isFilteringUpcomingMovies = false
    
    var selectedMovieIndex: Int!
    var moviesCount: Int {
        guard let movies = self.movies else { return 0 }
        return movies.count
    }
    var selectedMovie: Movie {
        return self.movies[selectedMovieIndex]
    }
    
    init(delegate: MoviesViewModelDelegate) {
        self.delegate = delegate
        self.network = Network(delegate: self)
        self.movies = [Movie]()
        self.allMovies = [Movie]()
        self.getPopularMovies()
    }
    
    func getPopularMovies() {
        self.network.getPopularMovies()
    }
    
    func setMovieInformation(on cell: MovieCollectionViewCell, with indexPath: IndexPath) {
        let movie = self.movies[indexPath.row]
        cell.titleLabel.text = movie.title
        
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "dd/MM/yyyy"
        cell.releaseDateLabel.text = dateFormatter.string(from: movie.releaseDate)
        
        DispatchQueue.global(qos: .background).async {
            let url = URL(string: "https://image.tmdb.org/t/p/w185_and_h278_bestv2\(movie.posterPath)")
            let data = try? Data(contentsOf: url!)
            let image = UIImage(data: data!)!
            DispatchQueue.main.async {
                cell.imageView.image = image
            }
        }
    }
    
    func toggleUpcomingMoviesFilter() {
        self.isFilteringUpcomingMovies = !self.isFilteringUpcomingMovies
        
        if isFilteringUpcomingMovies {
            self.movies = self.allMovies.filter() {
                $0.releaseDate.compare(Date()) == .orderedDescending
            }
        } else {
            self.movies = allMovies
        }
    }
    
}

extension MoviesViewModel: NetworkDelegate {
    func didFinishGettingPopularMovies(_ dictionary: [String : Any]) {
        if let results = dictionary[kResultsKey] as? [[String: Any]] {
            for result in results {
                let movie = Movie(result)
                self.movies.append(movie)
                self.allMovies.append(movie)
            }
        }
        
        self.delegate.didFinishGettingPopularMovies(self, dictionary: dictionary)
    }
    
    func didFailGettingPopularMovies(_ error: Error?) {
        self.delegate.didFailGettingPopularMovies(self, error: error)
    }
}
