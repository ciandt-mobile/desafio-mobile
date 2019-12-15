//
//  MoviesViewController.swift
//  MoviesChallenge
//
//  Created by Leonardo Bortolotti on 14/12/19.
//  Copyright © 2019 Leonardo Bortolotti. All rights reserved.
//

import UIKit
import Kingfisher

class MoviesViewController: UIViewController, UICollectionViewDelegate, UICollectionViewDataSource {
    
    @IBOutlet weak var segmentedControl: UISegmentedControl!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var moviesCollectionView: UICollectionView!
    
    let kMOVIE_CELL = "MovieCell"
    
    private var moviesResult: MoviesResult!
    private var popularArray: [Movie] = []
    private var upcomingArray: [Movie] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        moviesCollectionView.register(UINib(nibName: "MovieCollectionViewCell", bundle: nil), forCellWithReuseIdentifier: kMOVIE_CELL)
        moviesCollectionView.delegate = self
        moviesCollectionView.dataSource = self
        
        // Customiza o Segmented Control
        segmentedControl.layer.borderWidth = 1
        segmentedControl.layer.borderColor = UIColor.white.cgColor
        segmentedControl.setTitleTextAttributes([NSAttributedString.Key.foregroundColor: UIColor.white], for: .normal)
        segmentedControl.setTitleTextAttributes([NSAttributedString.Key.foregroundColor: UIColor.black], for: .selected)
        
        requestMovies()
    }
    
    // Muda a cor da Status Bar para branco
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    // MARK: - ValueChanged
    
    @IBAction func segmentedControlChanged(_ sender: UISegmentedControl) {
        if segmentedControl.selectedSegmentIndex == 0 {
            titleLabel.text = NSLocalizedString("Upcoming Movies", comment: "")
        }
        else {
            titleLabel.text = NSLocalizedString("Popular Movies", comment: "")
        }
        moviesCollectionView.reloadData()
    }
    
    // MARK: - Helper Methods
    
    private func requestMovies() {
        MovieAPI.requestPopularMovies { (data) in
            if let data = data {
                do {
                    self.moviesResult = try JSONDecoder().decode(MoviesResult.self, from: data)
                    self.popularArray = self.moviesResult.results ?? []
                    self.filterUpcomingMovies()
                    DispatchQueue.main.async {
                        self.moviesCollectionView.reloadData()
                    }
                } catch let error {
                    print(error)
                }
            }
        }
    }
    
    private func filterUpcomingMovies() {
        for movie in popularArray {
            let dateFormatter = DateFormatter()
            dateFormatter.dateFormat = "yyyy-MM-dd"
            let releaseDate = dateFormatter.date(from: movie.releaseDate ?? "")
            if releaseDate! > Date() {
                upcomingArray.append(movie)
            }
        }
    }
    
    private func getMovie(at indexPath: IndexPath) -> Movie {
        if segmentedControl.selectedSegmentIndex == 0 {
            return upcomingArray[indexPath.row]
        }
        return popularArray[indexPath.row]
    }
    
    // MARK: - UICollectionViewDataSource
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        switch segmentedControl.selectedSegmentIndex {
        case 0:
            return upcomingArray.count
        case 1:
            return popularArray.count
        default:
            return 0
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let movie = getMovie(at: indexPath)
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: kMOVIE_CELL, for: indexPath) as! MovieCollectionViewCell
        cell.imageView.kf.setImage(with: URL(string: Constants.kIMAGES_URL + (movie.posterPath ?? "")))
        cell.nameLabel.text = movie.title
        // Cria um DateFormatter para pegar a data no formato da API e converter para o formato do app
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let date = dateFormatter.date(from: movie.releaseDate ?? "")
        dateFormatter.dateFormat = "dd/MM/yy"
        cell.dateLabel.text = dateFormatter.string(from: date!)
        return cell
    }
    
    // MARK: - UICollectionViewDelegate
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let movie = getMovie(at: indexPath)
        let storyboard = UIStoryboard(name: "MovieDetails", bundle: nil)
        let movieDetailsViewController = storyboard.instantiateInitialViewController() as! MovieDetailsViewController
        movieDetailsViewController.id = movie.id
        show(movieDetailsViewController, sender: nil)
    }
    
}
