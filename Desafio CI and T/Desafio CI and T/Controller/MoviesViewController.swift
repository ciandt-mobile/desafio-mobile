//
//  MoviesViewController.swift
//  Desafio CI&T
//
//  Created by Mateus Kamoei on 31/07/19.
//  Copyright © 2019 Mateus Kamoei. All rights reserved.
//

import UIKit

class MoviesViewController: UIViewController {

    private let reuseIdentifier = "MovieCell"
    
    @IBOutlet weak var collectionView: UICollectionView!
    @IBOutlet weak var loadingView: UIView!
    
    var viewModel: MoviesViewModel!
    var selectedMovie: Movie!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.viewModel = MoviesViewModel(delegate: self)
    }
    
    @IBAction func upcomingMoviesSwitchValueChanged(_ sender: Any) {
        self.viewModel.setIsFilteringUpcomingMovies(!self.viewModel.isFilteringUpcomingMovies)
        self.collectionView.reloadData()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let movieDetailsVC = segue.destination as! MovieDetailsViewController
        let movieDetailsViewModel = MovieDetailsViewModel(self.selectedMovie, delegate: movieDetailsVC)
        movieDetailsVC.viewModel = movieDetailsViewModel
    }
}

extension MoviesViewController: MoviesViewModelDelegate {
    func didFinishGettingPopularMovies(_ viewModel: MoviesViewModel, dictionary: [String : Any]) {
        DispatchQueue.main.async {
            self.loadingView.isHidden = true
            self.collectionView.reloadData()
        }
    }
    
    func didFailGettingPopularMovies(_ viewModel: MoviesViewModel, error: Error?) {
        DispatchQueue.main.async {
            self.loadingView.isHidden = true
            self.presentTryAgainAlert(with: "An error occurred when loading the movies", and: {
                self.viewModel.getPopularMovies()
            })
        }
    }
}

extension MoviesViewController: UICollectionViewDataSource {
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.viewModel.movies.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = self.collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! MovieCollectionViewCell
        self.viewModel.setMovieInformation(on: cell, with: indexPath)
        
        return cell
    }
}

extension MoviesViewController: UICollectionViewDelegate {
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        self.selectedMovie = self.viewModel.movies[indexPath.row]
        self.performSegue(withIdentifier: "MovieDetailsSegue", sender: self)
    }
    
}
