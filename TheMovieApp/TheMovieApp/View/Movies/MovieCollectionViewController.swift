//
//  MovieCollectionViewController.swift
//  TheMovieApp
//
//  Created by Wilson Kim on 24/06/19.
//  Copyright © 2019 WilsonKim. All rights reserved.
//

import UIKit
import CoreData

class MovieCollectionViewController: UIViewController {

    @IBOutlet weak var moviesCollectionView: UICollectionView!
    @IBOutlet var stateView: StateFullView!
    let searchController = UISearchController(searchResultsController: nil)
    let collectionInsets:CGFloat = 8
    var favoriteMoviesDict:[Int64:Bool] = [:]
    var movies:[MovieViewModel] = [] {
        didSet {
            if (movies.count == 0) {
                stateView.setState(.empty("There no movies for this category. Try again later", image: nil))
            } else {
                moviesCollectionView.reloadData()
                stateView.setState(.normalLayout)
            }
        }
    }
    
    var filteredMovies:[MovieViewModel] = [] {
        didSet {
            if (filteredMovies.count == 0) {
                stateView.setState(.empty("No movies for this title", image: UIImage(named: "icon_search")))
            } else {
                moviesCollectionView.reloadData()
                stateView.setState(.normalLayout)
            }
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupNavigationBar()
        setupCollectionView()
        getMovies()
    }
    
    private func setupNavigationBar() {
        navigationController?.navigationBar.prefersLargeTitles = true
        searchController.delegate = self
        searchController.searchResultsUpdater = self
        searchController.obscuresBackgroundDuringPresentation = false
        searchController.searchBar.placeholder = "Search movie title"
        searchController.definesPresentationContext = true
        navigationItem.searchController = searchController
        navigationItem.hidesSearchBarWhenScrolling = false
    }
    
    private func setupCollectionView() {
        moviesCollectionView.delegate = self
        moviesCollectionView.dataSource = self
        moviesCollectionView.register(UINib(nibName: "MovieListCollectionViewCell", bundle: nil), forCellWithReuseIdentifier: CellReuse.MOVIE_CELL)
    }
    
    private func getMovies() {
        stateView.setErrorCompletion { self.getMovies() }
        let appProvider = AppProvider()
        stateView.setState(.loading("Carregando filmes populares..."))
        appProvider.makeRequest(.getPopularMovies, returnClass: GeneralMovieResponseModel.self, successCompletion: { (response) in
            self.movies = self.movies + response.getMoviesModel()
            self.getFavoriteMovies()
        }) { (error) in
            self.stateView.setState(.error(error.localizedDescription))
        }
    }
    
    private func getFavoriteMovies() {
        let coreDataManager = CoreDataManager()
        coreDataManager.fetch(MovieData.self, successCompletion: { (moviesData) in
            self.setFavoriteDict(self.getMoviesViewModel(moviesData))
            self.stateView.setState(.normalLayout)
        }) { (error) in
            self.stateView.setState(.normalLayout)
        }
    }
    
    private func setFavoriteDict(_ movies:[MovieViewModel]) {
        favoriteMoviesDict = [:]
        for movie in movies {
            favoriteMoviesDict[movie.id] = true
        }
        moviesCollectionView.reloadData()
    }
    
    private func getMoviesViewModel(_ moviesData:[MovieData]) -> [MovieViewModel] {
        var returnMovies:[MovieViewModel] = []
        for movieData in moviesData {
            returnMovies.append(bindToMovieViewModel(movieData))
        }
        return returnMovies
    }
    
    private func bindToMovieViewModel(_ movieData:MovieData) -> MovieViewModel {
        
        return MovieViewModel(_id: movieData.id,
                              _averageRating: movieData.averageRating,
                              _genresIds: movieData.genres as! [Int],
                              _originalTitle: movieData.originalTitle ?? "",
                              _backdropPath: movieData.backdropPath ?? "",
                              _isAdult: movieData.isAdult,
                              _popularity: movieData.popularity,
                              _posterPath: movieData.posterPath ?? "",
                              _title: movieData.title ?? "",
                              _overview: movieData.overview ?? "",
                              _originalLanguage: movieData.originalLanguage ?? "",
                              _voteCount: movieData.voteCount,
                              _releaseDate: movieData.releaseDate ?? Date(),
                              _isVideo: movieData.isVideo)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == Segue.SHOW_MOVIE_DETAIL {
            guard let destination = segue.destination as? MovieDetailViewController, let movie = sender as? MovieViewModel else {
                return
            }
            destination.movie = movie
            destination.delegate = self
        }
    }
}

extension MovieCollectionViewController: UICollectionViewDataSource, UICollectionViewDelegateFlowLayout, UICollectionViewDelegate {
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        searchController.isActive = false
        performSegue(withIdentifier: Segue.SHOW_MOVIE_DETAIL, sender: movies[indexPath.row])
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return !searchBarIsEmpty() ? filteredMovies.count : movies.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        var thisMovie:MovieViewModel?
        if (!searchBarIsEmpty() && filteredMovies.indices.contains(indexPath.row)) {
            thisMovie = filteredMovies[indexPath.row]
        }
        if (searchBarIsEmpty() && movies.indices.contains(indexPath.row)) {
            thisMovie = movies[indexPath.row]
        }
        
        let cell:MovieListCollectionViewCell = collectionView.dequeueReusableCell(withReuseIdentifier: CellReuse.MOVIE_CELL, for: indexPath) as! MovieListCollectionViewCell
        guard let movie = thisMovie else {
            return cell
        }
        cell.setMovieCell(movie)
        cell.setFavoriteButtonSelection(favoriteMoviesDict[movie.id] != nil)
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let cellWidth = (UIScreen.main.bounds.width - (2 * collectionInsets)) / 2
        let cellHeight = cellWidth * 1.75
        return CGSize(width: cellWidth, height: cellHeight);
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        return UIEdgeInsets(top: collectionInsets, left: collectionInsets, bottom: collectionInsets, right: collectionInsets);
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumInteritemSpacingForSectionAt section: Int) -> CGFloat {
        return 0
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return 0
    }
}

extension MovieCollectionViewController: UISearchControllerDelegate, UISearchResultsUpdating {
    func updateSearchResults(for searchController: UISearchController) {
        if let text = searchController.searchBar.text {
            if (!searchBarIsEmpty()) {
                filterContentForSearchText(text)
            }
        }
    }
    
    private func searchBarIsEmpty() -> Bool {
        return (searchController.searchBar.text?.isEmpty ?? true) || searchController.searchBar.text == ""
    }
    
    private func filterContentForSearchText(_ searchText: String) {
        filteredMovies = movies.filter({( movie : MovieViewModel) -> Bool in
            return movie.title.lowercased().contains(searchText.lowercased())
        })
        moviesCollectionView.reloadData()
    }
}

extension MovieCollectionViewController: MovieDetailDelegate {
    func didUpdateFavoriteStatus() {
        getFavoriteMovies()
    }
}
