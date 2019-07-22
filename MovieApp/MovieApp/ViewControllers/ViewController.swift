//
//  ViewController.swift
//  MovieApp
//
//  Created by Michele de Olivio Corrêa on 19/07/19.
//  Copyright © 2019 Michele de Olivio Corrêa. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var uiMoviesSelectionType: UILabel?
    @IBOutlet weak var uiCollectionView: UICollectionView?
    @IBOutlet weak var uiErrorView: UIView?
    @IBOutlet weak var uiLoadingView: UIView?
    @IBOutlet weak var uiLoadingIndicator: UIActivityIndicatorView?
    @IBOutlet weak var uiSegmentedControl: UISegmentedControl?
    
    private var numberOfPages = 1
    private var currentPage = 1
    private var selectedMovieSelectionType: MoviesSelectionType = .upcoming
    
    private enum ViewStatus {
        case loading
        case error
        case presenting
    }
    
    private var movies = [Movie]() {
        didSet {
            uiCollectionView?.reloadData()
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.accessibilityIdentifier = "home"
        
        self.title = "Home"
        
        fetchMovies(type: selectedMovieSelectionType)
    }
    
    private func fetchMovies(type: MoviesSelectionType) {
        uiMoviesSelectionType?.text = type.labelText
        
        setupViewStatus(status: .loading)
        
        MovieStore.shared.fetchMovies(for: type, successHandler: { [weak self] (response) in
            self?.numberOfPages = response.totalPages
            self?.currentPage = response.page
            self?.movies = response.results
            
            self?.setupViewStatus(status: .presenting)
            }, errorHandler: {[weak self] (error) in
                self?.setupViewStatus(status: .error)
        })
    }
    
    private func setupViewStatus(status: ViewStatus) {
        let isLoading = status == .loading
        let isError = status == .error
        let isPresenting = status == .presenting
        
        if isLoading {
            uiLoadingIndicator?.startAnimating()
        } else {
            DispatchQueue.main.async {
                self.uiLoadingIndicator?.stopAnimating()
            }
        }
        
        DispatchQueue.main.async {
            self.uiErrorView?.isHidden = !isError
            self.uiLoadingView?.isHidden = !isLoading
            self.uiCollectionView?.isHidden = !isPresenting
        }
    }
    
    @IBAction func uiTryAgainAction(_ sender: Any) {
        fetchMovies(type: selectedMovieSelectionType)
    }
    
    @IBAction func uiChangeMoviesSelectionAction(_ sender: UISegmentedControl) {
        
        guard let segmentedControl = uiSegmentedControl else {
            return
        }
        
        movies = []

        selectedMovieSelectionType = MoviesSelectionType(index: segmentedControl.selectedSegmentIndex) ?? MoviesSelectionType.upcoming
        
        fetchMovies(type: selectedMovieSelectionType)
    }
}

extension ViewController: UICollectionViewDataSource, UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return movies.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "movieCollectionViewCell", for: indexPath) as! MovieCollectionViewCell
        let movie = movies[indexPath.item]
        cell.setup(movie: movie)
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let viewController = MovieDetailsViewController()
        viewController.movieId = movies[indexPath.item].id
        self.navigationController?.pushViewController(viewController, animated: true)
    }
    
    func collectionView(_ collectionView: UICollectionView, willDisplay cell: UICollectionViewCell, forItemAt indexPath: IndexPath) {
        let hasMorePagesToLoad = currentPage < numberOfPages
        let isLastElement = indexPath.row == movies.count - 1
        if (hasMorePagesToLoad && isLastElement) {
            loadMore()
        }
    }
    
    private func loadMore() {
        let page = currentPage + 1
        
        MovieStore.shared.fetchMovies(for: selectedMovieSelectionType, page: page, successHandler: { [weak self] (response) in
            self?.currentPage = response.page
            self?.movies += response.results
                        
            }, errorHandler: { _ in
                // Nothing.
        })
    }
}

