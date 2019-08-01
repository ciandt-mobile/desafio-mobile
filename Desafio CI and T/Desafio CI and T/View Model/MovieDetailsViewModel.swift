//
//  MovieDetailsViewModel.swift
//  Desafio CI&T
//
//  Created by Mateus Kamoei on 31/07/19.
//  Copyright © 2019 Mateus Kamoei. All rights reserved.
//

import Foundation
import UIKit

protocol MovieDetailsViewModelDelegate {
    func didFinishGettingMovieDetails(_ viewModel: MovieDetailsViewModel)
    func didFailGettingMovieDetails(_ viewModel: MovieDetailsViewModel, error: Error?)
    func didFinishGettingMovieCredits(_ viewModel: MovieDetailsViewModel)
    func didFailGettingMovieCredits(_ viewModel: MovieDetailsViewModel, error: Error?)
    func didFinishGettingImage(_ viewModel: MovieDetailsViewModel)
}

class MovieDetailsViewModel {
    
    private var network: Network!
    private let movie: Movie
    private let delegate: MovieDetailsViewModelDelegate
    
    var movieTitle: String {
        return movie.title
    }
    var movieInformation = ""
    var overview = ""
    var image: UIImage?
    var castCount: Int {
        guard let cast = movie.cast else { return 0 }
        return cast.count
    }
    
    init(_ movie: Movie, delegate: MovieDetailsViewModelDelegate) {
        self.movie = movie
        self.delegate = delegate
        self.network = Network(delegate: self)
        self.getMovieDetails()
        self.getMovieCredits()
        self.getMovieImage()
    }
    
    func getMovieDetails() {
        self.network.getMovieDetails(self.movie.id)
    }
    
    func getMovieCredits() {
        self.network.getMovieCredits(self.movie.id)
    }
    
    func getMovieImage() {
        guard let backdropPath = self.movie.backdropPath else { return }
        
        DispatchQueue.global(qos: .background).async {
            let url = URL(string: "https://image.tmdb.org/t/p/w1280\(backdropPath)")
            let data = try? Data(contentsOf: url!)
            let image = UIImage(data: data!)!
            DispatchQueue.main.async {
                self.image = image
                self.delegate.didFinishGettingImage(self)
            }
        }
    }
    
    func setMovieInformation() {
        guard let runtime = self.movie.runtime, let genres = self.movie.genres else { return }
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy"
        let yearString = dateFormatter.string(from: self.movie.releaseDate)
        self.movieInformation = "\(yearString) | \(runtime)m | \(genres.joined(separator: ", "))"
    }
    
    func setMovieOverview() {
        guard let overview = self.movie.overview else { return }
        self.overview = overview
    }
    
    func setCastInformation(on cell: CastMemberTableViewCell, with indexPath: IndexPath) {
        let castMember = self.movie.cast![indexPath.row]
        cell.nameLabel.text = castMember.name
        cell.castMemberImageView.image = nil
        
        guard let profilePath = castMember.profilePath else { return }
        DispatchQueue.global(qos: .background).async {
            let url = URL(string: "https://image.tmdb.org/t/p/w185_and_h278_bestv2\(profilePath)")
            let data = try? Data(contentsOf: url!)
            let image = UIImage(data: data!)!
            DispatchQueue.main.async {
                cell.castMemberImageView.image = image
            }
        }
    }
}

extension MovieDetailsViewModel: NetworkDelegate {
    
    func didFinishGettingMovieDetails(_ dictionary: [String : Any]) {
        self.movie.setMovieDetails(dictionary)
        self.setMovieInformation()
        self.setMovieOverview()
        self.delegate.didFinishGettingMovieDetails(self)
    }
    
    func didFailGettingMovieDetails(_ error: Error?) {
        self.delegate.didFailGettingMovieDetails(self, error: error)
    }
    
    func didFinishGettingMovieCredits(_ dictionary: [String : Any]) {
        self.movie.setCast(dictionary)
        self.delegate.didFinishGettingMovieCredits(self)
    }
    
    func didFailGettingMovieCredits(_ error: Error?) {
        self.delegate.didFailGettingMovieCredits(self, error: error)
    }
}
