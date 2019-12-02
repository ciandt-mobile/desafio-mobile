//
//  HomeViewController.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 30/11/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import UIKit

class HomeViewController: CustomViewController {

    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    @IBOutlet weak var scrollView: UIScrollView!

    internal var viewModel = HomeViewModel()
    private let movieDetailsViewModel = MovieDetailsViewModel()
    internal var movieDetails: Movie?
    internal var refreshControl = UIRefreshControl()

    override func viewDidLoad() {
        super.viewDidLoad()

        fetchMoviesGeneric(endPoint: Constants.nowPlayingEndPoint)
        fetchMoviesGeneric(endPoint: Constants.popularEndPoint)
        fetchMoviesGeneric(endPoint: Constants.upComingEndPoint)
        setupRefreshControl()
        GenresManager.sharedInstance().fetchGenres(endPoint: Constants.genreEndPoint)
    }

    func fetchMoviesGeneric(endPoint: String, page: Int = 1) {
        activityIndicator.isHidden = false
        MovieManager.sharedInstance().fetchMovies(endPoint: endPoint, page: page) { [weak self] (result) in
            guard let weakSelf = self else { return }
            switch result {
            case .failure(let error):
                print("[ERROR] Ops! \(String(describing: error))")
                weakSelf.showAlertView(title: "Ops!", message: String(describing: error))
                weakSelf.finishedRefreshing()
                weakSelf.activityIndicator.isHidden = true

            case .success(let movieResult):
                DispatchQueue.main.async {
                    if endPoint == Constants.nowPlayingEndPoint {
                        weakSelf.viewModel.nowPlayingViewModel.setMovieResult(movieResult)
                        weakSelf.viewModel.nowPlayingViewModel.setMovies(movieResult.results)
                    } else if endPoint == Constants.popularEndPoint {
                        weakSelf.viewModel.popularViewModel.setMovieResult(movieResult)
                        weakSelf.viewModel.popularViewModel.setMovies(movieResult.results)
                    } else if endPoint == Constants.upComingEndPoint {
                        weakSelf.viewModel.upComingViewModel.setMovieResult(movieResult)
                        weakSelf.viewModel.upComingViewModel.setMovies(movieResult.results)
                    }

                    weakSelf.finishedRefreshing()
                    weakSelf.activityIndicator.isHidden = true
                }
            }
        }
    }

    @IBAction func showMore(_ sender: UIButton) {
        self.tabBarController?.selectedIndex = sender.tag
    }

    // MARK: - Navigation

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == NowPlayingViewController.segueIdentifier {
            guard let nowPlayingViewController = segue.destination as? NowPlayingViewController else { return }
            nowPlayingViewController.delegate = self
            nowPlayingViewController.viewModel = viewModel.nowPlayingViewModel
        } else if segue.identifier == PopularViewController.segueIdentifier {
            guard let popularViewController = segue.destination as? PopularViewController else { return }
            popularViewController.delegate = self
            popularViewController.viewModel = viewModel.popularViewModel
        } else if segue.identifier == UpComingViewController.segueIdentifier {
            guard let upComingViewController = segue.destination as? UpComingViewController else { return }
            upComingViewController.delegate = self
            upComingViewController.viewModel = viewModel.upComingViewModel
        }  else if segue.identifier == MovieDetailsViewController.segueIdentifier {
            guard let movieDetailsViewController = segue.destination as? MovieDetailsViewController else { return }
            if let movieDetails = self.movieDetails {
                print(movieDetails)
                movieDetailsViewModel.setMovieDetails(movie: movieDetails)
                movieDetailsViewController.viewModel = movieDetailsViewModel
            }
        }
    }

}

