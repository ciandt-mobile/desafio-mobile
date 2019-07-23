//
//  MoviesViewController.swift
//  MobileChallengeCIET
//
//  Created by Guilherme C Rosa on 22/07/19.
//  Copyright © 2019 Guilherme C Rosa. All rights reserved.
//

import UIKit

enum MovieCategory: String {
    case Upcoming = "Upcoming Movies"
    case Popular = "Popular Movies"
}

class MoviesViewController: UIViewController {

    @IBOutlet weak var segmented: UISegmentedControl!
    @IBOutlet weak var labelTitle: UILabel!
    @IBOutlet weak var collection: UICollectionView!
    
    private var category = MovieCategory.Upcoming
    private var datasource = MoviesCollection(state: .Loading, movies: nil) {
        didSet { self.setupCollection() }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationController?.setNavigationBarHidden(true, animated: true)
        
        self.setupCollection()
        self.serviceMovies()
    }
    
    private func setupCollection() {
        
        self.datasource.delegate = self
        self.datasource.registerCells(collection: self.collection)
        
        self.collection.dataSource = self.datasource
        self.collection.delegate = self.datasource
    }
    
    private func serviceMovies() {
        self.datasource = MoviesCollection(state: .Loading, movies: nil)
        
        let service = TheMovieDBService()
        service.getMovies(category: self.category) { (list, error) in
            if let _ = error {
                self.datasource = MoviesCollection(state: .Error, error: error.debugDescription)
            } else {
                if let movies = list {
                    if movies.isEmpty {
                        self.datasource = MoviesCollection(state: .Empty, error: "Empty List")
                    } else {
                        self.datasource = MoviesCollection(state: .Success, movies: movies)
                    }
                } else {
                    self.datasource = MoviesCollection(state: .Error, error: "Parsing Error")
                }
            }
        }
    }
    
    @IBAction func segmentedAction(_ sender: Any) {
        switch segmented.selectedSegmentIndex {
            case 0:
                self.category = .Upcoming
                self.labelTitle.text = MovieCategory.Upcoming.rawValue
            case 1:
                self.category = .Popular
                self.labelTitle.text = MovieCategory.Popular.rawValue
            default:
                break
        }
        
        self.serviceMovies()
    }
}

extension MoviesViewController: MoviesCollectionProtocol {
    func collectionDidSelectItem(movie: Movie) {
        let vc = MovieDetailViewController(movie: movie)
        self.navigationController?.pushViewController(vc, animated: true)
    }
}
