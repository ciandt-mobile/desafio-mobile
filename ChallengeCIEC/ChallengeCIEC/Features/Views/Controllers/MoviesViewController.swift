//
//  MoviesViewController.swift
//  ChallengeCIEC
//
//  Created by Guilherme Camilo Rosa on 25/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import UIKit

class MoviesViewController: UIViewController {
    
    @IBOutlet weak var segmented: UISegmentedControl!
    @IBOutlet weak var labelTitle: UILabel!
    @IBOutlet weak var collection: UICollectionView!
    
    private var presenter = MoviesPresenter()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setupPresenter()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(true)
        self.navigationController?.setNavigationBarHidden(true, animated: true)
        self.navigationController?.navigationBar.tintColor = .darkGray
    }
    
    private func setupPresenter() {
        self.presenter = MoviesPresenter()
        self.presenter.delegate = self
        self.presenter.fetchMovies()
    }
    
    private func setupCollection(builder: MoviesCollectionBuilder) {
        
        builder.collection = self.collection
        builder.delegate = self
        
        self.collection.dataSource = builder
        self.collection.delegate = builder
        self.collection.reloadData()
    }
    
    @IBAction func segmentedAction(_ sender: Any) {
        self.presenter.changeCategory(sender: segmented.selectedSegmentIndex)
    }
}

extension MoviesViewController: MoviesCollectionBuilderProtocol {
    func collectionDidSelectItem(movie: Movie) {
        let vc = MovieDetailViewController(movie: movie)
        self.navigationController?.pushViewController(vc, animated: true)
    }
}

extension MoviesViewController: MoviesPresenterDelegate {
    func didChangeCategory(category: MovieCategory) {
        self.labelTitle.text = category.rawValue
        self.presenter.fetchMovies()
    }
    
    func didUpdateCollectBuilder(builder: MoviesCollectionBuilder) {
        self.setupCollection(builder: builder)
    }
}
