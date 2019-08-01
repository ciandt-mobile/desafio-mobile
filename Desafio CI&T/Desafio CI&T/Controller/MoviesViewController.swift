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
    
    var viewModel: MoviesViewModel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.viewModel = MoviesViewModel(delegate: self)
    }
    
    @IBAction func upcomingMoviesSwitchValueChanged(_ sender: Any) {
        self.viewModel.setIsFilteringUpcomingMovies(!self.viewModel.isFilteringUpcomingMovies)
        self.collectionView.reloadData()
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}

extension MoviesViewController: MoviesViewModelDelegate {
    func didFinishGettingPopularMovies(_ viewModel: MoviesViewModel, dictionary: [String : Any]) {
        DispatchQueue.main.async {
            self.collectionView.reloadData()
        }
    }
    
    func didFailGettingPopularMovies(_ viewModel: MoviesViewModel, error: Error?) {
        
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
    
}
