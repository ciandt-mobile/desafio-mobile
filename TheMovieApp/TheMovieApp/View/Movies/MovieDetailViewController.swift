//
//  MovieDetailViewController.swift
//  TheMovieApp
//
//  Created by Wilson Kim on 26/06/19.
//  Copyright © 2019 WilsonKim. All rights reserved.
//

import UIKit

protocol MovieDetailDelegate {
    func didUpdateFavoriteStatus()
}

class MovieDetailViewController: BaseViewController {
    
    @IBOutlet weak var mainImageView: UIImageView!
    @IBOutlet weak var movieTitleLabel: UILabel!
    @IBOutlet weak var releaseDateLabel: UILabel!
    @IBOutlet weak var genresLabel: UILabel!
    @IBOutlet weak var movieOverviewLabel: UILabel!
    @IBOutlet weak var favoriteButton: FavoriteButton!
    
    var movie:MovieViewModel?
    var delegate:MovieDetailDelegate?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        navigationItem.largeTitleDisplayMode = .never
        getGenres()
        setupLabels()
    }
    
    private func getGenres() {
        let provider = AppProvider()
        setRetryErrorBlock { self.getGenres() }
        showLoadingInView(withMessage: "Carregando informações do filme...")
        provider.makeRequest(.getGenreList, returnClass: GeneralGenreResponseModel.self, successCompletion: { (response) in
            self.setGenreText(response.getGenreModels())
            self.setFavoriteButton()
        }) { (error) in
            self.showError(error)
        }
    }
    
    private func setFavoriteButton() {
        guard let movie = movie else {
            return
        }
        let manager = CoreDataManager()
        let predicate = NSPredicate(format: "id = %@", argumentArray: [movie.id])
        manager.fetch(MovieData.self, predicate: predicate, successCompletion: { (moviesData) in
            self.favoriteButton.isSelected = moviesData.count > 0
            self.setNormalLayout()
        }) { (error) in
            self.showAlert(_title: "Error", _message: "Could not load favorite status")
        }
    }
    
    private func setGenreText(_ genres:[GenreModel]) {
        var genreDict:[Int:String] = [:]
        for genre in genres {
            genreDict[genre.id] = genre.name
        }
        genresLabel.text = movie!.genresIds.compactMap { (id) -> String? in
            if let str = genreDict[id] {
                return str
            }
            return nil
            }.joined(separator: ", ")
    }
    
    private func setupLabels() {
        guard let movie = movie else {
            return
        }
        title = movie.title
        mainImageView.loadImageFrom(path: movie.posterPath)
        movieTitleLabel.text = movie.title
        releaseDateLabel.text = "Premiere: \(movie.releaseDate.getYearValue())"
        movieOverviewLabel.text = movie.overview
    }
    
    @IBAction func favoriteMovieButtonClicked(_ sender: UIButton) {
        if (sender.isSelected) {
            removeFavoriteMovie()
        } else {
            saveFavoriteMovie()
        }
    }
    
    private func saveFavoriteMovie() {
        guard let movie = movie else {
            return
        }
        showLoadingInView(withMessage: "Adding to favorites...")
        let coreDataManger = CoreDataManager()
        coreDataManger.saveObject(bindToMovieData(movie), successCompletion: {
            self.favoriteButton.isSelected = true
            self.setNormalLayout()
            self.delegate?.didUpdateFavoriteStatus()
        }) { (error) in
            self.setNormalLayout()
            self.favoriteButton.isSelected = false
            self.showAlert(_title: "Error", _message: "Movie was not added to favorites. Please try again later.")
        }
    }
    
    private func removeFavoriteMovie() {
        guard let movie = movie else {
            return
        }
        let manager = CoreDataManager()
        let predicate = NSPredicate(format: "id = %@", argumentArray: [movie.id])
        showLoadingInView(withMessage: "Removing from favorites...")
        manager.delete(MovieData.self, predicate: predicate, successCompletion: {
            self.favoriteButton.isSelected = false
            self.setNormalLayout()
            self.delegate?.didUpdateFavoriteStatus()
        }) { (error) in
            self.showAlert(_title: "Error", _message: "Could not remove favorite status")
        }
    }
    
    private func bindToMovieData(_ movie: MovieViewModel) -> MovieData {
        let newMovie = MovieData(context: getCoreDataContext())
        newMovie.id = movie.id
        newMovie.averageRating = movie.averageRating
        newMovie.genres = movie.genresIds as NSObject
        newMovie.originalTitle = movie.originalTitle
        newMovie.backdropPath = movie.backdropPath
        newMovie.isAdult = movie.isAdult
        newMovie.popularity = movie.popularity
        newMovie.posterPath = movie.posterPath
        newMovie.title = movie.title
        newMovie.overview = movie.overview
        newMovie.originalLanguage = movie.originalLanguage
        newMovie.voteCount = Int64(movie.voteCount)
        newMovie.releaseDate = movie.releaseDate
        newMovie.isVideo = movie.isVideo
        
        return newMovie
    }
}
