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
    private var manager = MoviesCollectionManager(state: .Loading, movies: nil) {
        didSet { self.setupCollection() }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationController?.setNavigationBarHidden(true, animated: false)
        
        self.setupCollection()
        self.serviceMovies()
    }
    
    private func setupCollection() {
        
        self.manager.delegate = self
        self.manager.registerCells(collection: self.collection)
        
        self.collection.dataSource = self.manager
        self.collection.delegate = self.manager
    }
    
    private func serviceMovies() {
        self.manager = MoviesCollectionManager(state: .Loading, movies: nil)
        
        let service = TheMovieDBService()
        service.getMovies(category: self.category) { (list, error) in
            if let err = error {
                self.manager = MoviesCollectionManager(state: .Error, error: err.localizedDescription)
            } else {
                if let movies = list {
                    if movies.isEmpty {
                        self.manager = MoviesCollectionManager(state: .Empty, error: "Empty List")
                    } else {
                        self.manager = MoviesCollectionManager(state: .Success, movies: movies)
                    }
                } else {
                    self.manager = MoviesCollectionManager(state: .Error, error: "Parsing Error")
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

extension MoviesViewController: MoviesCollectionManagerProtocol {
    func collectionDidSelectItem(movie: Movie) {
        let vc = MovieDetailViewController(movie: movie)
        self.navigationController?.pushViewController(vc, animated: true)
    }
}
