import Foundation
import UIKit
import SwiftMessages

class MovieListController: UIViewController, HasCustomView, UICollectionViewDataSource, UICollectionViewDelegate, MovieFilterChange {
    
    typealias CustomView = MovieListView
    private var movies : [Movie] = []
    var loadingView = UIView()
    var currentPage = 1
    var totalPages = 0
    var filter : MovieFilter = .popular
    
    override func loadView() {
        let movieListView = MovieListView()
        view = movieListView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        customView.render(self, movieFilterChange: self)
        customView.collectionView.delegate = self
        customView.collectionView.dataSource = self
        getMovieList(page: currentPage)
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return movies.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: customView.cellId, for: indexPath) as! MovieListCell
        let currentMovie = movies[indexPath.row]
        cell.movie = currentMovie
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let movieSelected = movies[indexPath.row]
        let movieDetailController = MovieDetailController(nibName: nil, bundle: nil)
        
        loadingView = displayLoading(onView: self.view)
        MovieAPI.getMovieDetail(id: movieSelected.id, success: { (response) in
            self.removeLoading(self.loadingView)
            movieSelected.genres = response.genres
            movieSelected.runtime = response.runtime
            movieSelected.cast = response.credits?.cast
            movieDetailController.movie = movieSelected
            self.navigationController?.pushViewController(movieDetailController, animated: true)
        }) { (errorMessage) in
            self.removeLoading(self.loadingView)
            Alert.show(message: errorMessage, type: .error)
        }
    }
    
    func scrollViewDidEndDragging(_ scrollView: UIScrollView, willDecelerate decelerate: Bool) {
        let offsetY = scrollView.contentOffset.y
        let contentHeight = scrollView.contentSize.height
        
        if offsetY > contentHeight - scrollView.frame.size.height {
            if (currentPage < totalPages) {
                getMovieList(page: currentPage + 1)
            }
        }
    }
    
    func getMovieList(page : Int) {
        loadingView = displayLoading(onView: self.view)
        MovieAPI.getMovies(page, filter, success: { (response) in
            self.currentPage = response.page
            self.totalPages = response.totalPages
            
            if (self.movies.count > 0) {
                self.movies.append(contentsOf: response.results)
            } else {
                self.movies = response.results
            }
            
            self.customView.collectionView.reloadData()
            self.removeLoading(self.loadingView)
        }) { (errorMessage) in
            self.removeLoading(self.loadingView)
            Alert.show(message: errorMessage, type: .error)
        }
    }
    
    func updateMovieList() {
        filter = filter == .popular ? .upcoming : .popular
        movies = []
        customView.collectionView.reloadData()
        customView.updateNavigationBar(filter)
        getMovieList(page: 1)
    }
}

