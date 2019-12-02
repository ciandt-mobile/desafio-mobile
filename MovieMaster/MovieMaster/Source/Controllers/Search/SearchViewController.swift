//
//  SearchViewController.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 01/12/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import UIKit

class SearchViewController: CustomViewController, Identifiable {

    @IBOutlet weak var searchCollectionView: UICollectionView!
    @IBOutlet weak var infoLabel: UILabel!
    @IBOutlet weak var searchBar: UISearchBarX!
    var searchString: String = ""

    private let movieDetailsViewModel = MovieDetailsViewModel()
    internal var viewModel = SearchViewModel()
    internal var collectionViewFlowLayout: UICollectionViewFlowLayout!
    internal var movieSelected: Movie?
    internal var fetchingMore = false
    internal var endReached = false

    override func viewDidLoad() {
        super.viewDidLoad()

        setupViewModel()
        setupCollectionView()
        setupSearchBar()
        registerNib()
    }

    override func viewWillLayoutSubviews() {
        super.viewWillLayoutSubviews()
        setupCollectionViewItemSize()
    }

    private func setupViewModel() {
        self.viewModel.delegate = self
    }

    internal func searchMovie(page: Int = 1, query: String)  {
        fetchingMore = true
        MovieManager.sharedInstance().queryMovies(endPoint: Constants.searchEndPoint, page: page, query: query) { [weak self] (result) in
            guard let weakSelf = self else { return }
            switch result {
            case .failure(let error):
                print("[ERROR] Ops! \(String(describing: error))")
                weakSelf.showAlertView(title: "Ops!", message: String(describing: error))

            case .success(let data):
                DispatchQueue.main.async {

                    weakSelf.viewModel.setMovieResult(data)

                    if weakSelf.viewModel.getMoviesCount() ?? 0 == 0 {
                        weakSelf.viewModel.setMovies(data.results)
                    } else {
                        weakSelf.viewModel.updateMovies(data.results)
                    }

                    weakSelf.viewModel.setPagination(data)
                    weakSelf.fetchingMore = false
                }
            }
        }
    }

    // MARK: - Navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == MovieDetailsViewController.segueIdentifier {
            guard let movieDetailsViewController = segue.destination as? MovieDetailsViewController else { return }
            
            if let movieSelected = self.movieSelected {
                movieDetailsViewModel.setMovieDetails(movie: movieSelected)
                movieDetailsViewController.viewModel = movieDetailsViewModel
            }
        }
    }

}
