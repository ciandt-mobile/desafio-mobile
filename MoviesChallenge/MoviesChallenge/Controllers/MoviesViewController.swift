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
    private var moviesArray: [Movie] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        moviesCollectionView.register(UINib(nibName: "MovieCollectionViewCell", bundle: nil), forCellWithReuseIdentifier: kMOVIE_CELL)
        moviesCollectionView.delegate = self
        moviesCollectionView.dataSource = self
        
        segmentedControl.layer.borderWidth = 1
        segmentedControl.layer.borderColor = UIColor.white.cgColor
        segmentedControl.setTitleTextAttributes([NSAttributedString.Key.foregroundColor: UIColor.white], for: .normal)
        segmentedControl.setTitleTextAttributes([NSAttributedString.Key.foregroundColor: UIColor.black], for: .selected)
        
        requestMovies()
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    @IBAction func segmentedControlChanged(_ sender: UISegmentedControl) {
        
    }
    
    // MARK: - Helper Methods
    
    private func requestMovies() {
        MovieAPI.requestPopularMovies { (data) in
            if let data = data {
                do {
                    self.moviesResult = try JSONDecoder().decode(MoviesResult.self, from: data)
                    self.moviesArray = self.moviesResult.results ?? []
                    DispatchQueue.main.async {
                        self.moviesCollectionView.reloadData()
                    }
                } catch let error {
                    print(error)
                }
            }
            else {
                
            }
        }
    }
    
    // MARK: - UICollectionViewDataSource
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return moviesArray.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let movie = moviesArray[indexPath.row]
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: kMOVIE_CELL, for: indexPath) as! MovieCollectionViewCell
        cell.imageView.kf.setImage(with: URL(string: Constants.kIMAGES_URL + (movie.posterPath ?? "")))
        cell.nameLabel.text = movie.title
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let date = dateFormatter.date(from: movie.releaseDate ?? "")
        dateFormatter.dateFormat = "dd/MM/yy"
        cell.dateLabel.text = dateFormatter.string(from: date!)
        return cell
    }
    
}
