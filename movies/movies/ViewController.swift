//
//  ViewController.swift
//  movies
//
//  Created by Arthur Silva on 11/29/19.
//  Copyright © 2019 Arthur Silva. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    // MARK: IBOutlets
    @IBOutlet weak var moviesTableView: UITableView!
    @IBOutlet weak var errorView: UIView!
    @IBOutlet weak var errorMessageLabel: UILabel!
    @IBOutlet weak var movieFilterSegmentedControl: UISegmentedControl!

    private var isFetchingMoreMovies: Bool = false
    private var gotErrorFetchingMovies: Bool = false
    private var currentPage: Int = 1
    private var totalPages: Int = 0
    private let movieService = MovieDbService()
    private var movies: [Movie] = []
    private var upcomingMovies: [Movie] = []

    override func viewDidLoad() {
        super.viewDidLoad()

        movieService.getPopularMovies(page: self.currentPage, onSuccess: { popularMovies in
            self.movies = popularMovies.results
            self.totalPages = popularMovies.total_pages
            self.moviesTableView.reloadData()
            self.filterUpcomingMovies(movies: popularMovies.results)
        }) {
            self.moviesTableView.isHidden.toggle()
            self.errorView.isHidden.toggle()
            self.errorMessageLabel.text = Constants.POPULAR_MOVIES_ERROR_MESSAGE
        }

        setupView()
    }

    func setupView() {

        let movieNib = UINib(nibName: PopularMoviesTableTableViewCell.viewIdentifier, bundle: nil)
        self.moviesTableView.register(movieNib, forCellReuseIdentifier: PopularMoviesTableTableViewCell.viewIdentifier)

        let newBatchNib = UINib(nibName: NewMovieBatchMessageTableViewCell.viewIdentifier, bundle: nil)
        self.moviesTableView.register(newBatchNib, forCellReuseIdentifier: NewMovieBatchMessageTableViewCell.viewIdentifier)
    }

    func fetchMoreMovies() {
        self.isFetchingMoreMovies = true

        if !gotErrorFetchingMovies {
            self.currentPage += 1
            self.moviesTableView.reloadData()
        }
        else {
            NotificationCenter.default.post(name: .didStartedDownloading, object: self)
        }

        self.movieService.getPopularMovies(page: self.currentPage, onSuccess: { popularMovies in
            self.isFetchingMoreMovies = false
            self.gotErrorFetchingMovies = false
            self.movies.append(contentsOf: popularMovies.results)
            self.filterUpcomingMovies(movies: popularMovies.results)
            self.moviesTableView.reloadData()
        }) {
            self.isFetchingMoreMovies = false
            self.gotErrorFetchingMovies = true
            NotificationCenter.default.post(name: .didReceivedAnError, object: self)
        }
    }

    // MARK: IBActions
    @IBAction func movieFilterChangedValue(_ sender: UISegmentedControl) {
        self.moviesTableView.reloadData()

        if self.movieFilterSegmentedControl.selectedSegmentIndex == 1 {
            if self.upcomingMovies.count == 0 {
                self.fetchMoreMovies()
            }
        }
    }

    func filterUpcomingMovies(movies: [Movie]) {

        let today = Date()

        for movie in movies {
            if let releaseDateComponents = movie.releaseDateComponents, let releaseDate = Calendar.current.date(from: releaseDateComponents) {
                if releaseDate > today {
                    self.upcomingMovies.append(movie)
                }
            }
        }
    }
}

// MARK: UITableView extensions
extension ViewController: UITableViewDelegate {

    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if indexPath.section == 0 {
            return Constants.MOVIES_CELL_HEIGHT
        }
        else {
            return Constants.MOVIES_CELL_NEW_FETCH_HEIGHT
        }
    }

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {

        tableView.deselectRow(at: indexPath, animated: false)

        if indexPath.section == 1 {
            return
        }

        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        guard let movieDetailsVC = storyboard.instantiateViewController(withIdentifier: MovieDetailsViewController.viewIdentifier)
            as? MovieDetailsViewController else {
            return
        }

        if self.movieFilterSegmentedControl.selectedSegmentIndex == 0 {
            movieDetailsVC.movie = self.movies[indexPath.row]
        }
        else {
            movieDetailsVC.movie = self.upcomingMovies[indexPath.row]
        }

        self.navigationController?.pushViewController(movieDetailsVC, animated: true)
    }

    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        let offsetY = scrollView.contentOffset.y
        let contentHeight = scrollView.contentSize.height

        if offsetY > contentHeight - scrollView.frame.height {
            if !self.isFetchingMoreMovies && self.currentPage + 1 <= self.totalPages {
                self.fetchMoreMovies()
            }
        }
    }
}

extension ViewController: UITableViewDataSource {

    func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if section == 0 {
            if movieFilterSegmentedControl.selectedSegmentIndex == 0 {
                return self.movies.count
            }
            else {
                return self.upcomingMovies.count
            }
        }

        if self.isFetchingMoreMovies {
            return 1
        }

        return 0
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {

        if indexPath.section == 0 {
            guard let cell = tableView.dequeueReusableCell(withIdentifier: PopularMoviesTableTableViewCell.viewIdentifier, for: indexPath)
                as? PopularMoviesTableTableViewCell else {
                fatalError("Could not dequeue PopularMoviesTableViewCell")
            }

            if self.movieFilterSegmentedControl.selectedSegmentIndex == 0 {
                cell.setMovie(self.movies[indexPath.row])
            }
            else {
                cell.setMovie(self.upcomingMovies[indexPath.row])
            }

            return cell
        }
        else {
            guard let cell = tableView.dequeueReusableCell(withIdentifier: NewMovieBatchMessageTableViewCell.viewIdentifier, for: indexPath)
                as? NewMovieBatchMessageTableViewCell else {
                    fatalError("Could not dequeue NewMovieBatchMessageTableViewCell")
            }

            cell.loadingIndicator.startAnimating()
            cell.messageLabel.text = Constants.FETCHING_NEW_MOVIES_MESSAGE

            return cell
        }
    }
}
