//
//  PopularViewController.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 30/11/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import UIKit

protocol PopularDelegate: class {
    func didSelectItemAt(with movie: Movie)
}

class PopularViewController: CustomViewController, Identifiable {

    @IBOutlet weak var popularCollectionView: UICollectionView!
    private let movieDetailsViewModel = MovieDetailsViewModel()
    var viewModel: PopularViewModel? {
        didSet {
            setupViewModel()
            isViewModelSet = true
        }
    }
    internal var collectionViewFlowLayout: UICollectionViewFlowLayout!
    private var isViewModelSet: Bool = false
    internal var isDelegateSet: Bool = false
    internal var movieSelected: Movie?
    internal var fetchingMore = false
    internal var endReached = false
    weak var delegate: PopularDelegate? {
        didSet {
            self.isDelegateSet = true
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        initViewModel()
        fetchPopular()
        setupCollectionView()
        registerNib()
    }

    override func viewWillAppear(_ animated: Bool) {
        if viewModel != nil {
            updateView()
        }
    }

    override func viewWillLayoutSubviews() {
        super.viewWillLayoutSubviews()
        setupCollectionViewItemSize()
    }

    private func setupViewModel() {
        self.viewModel?.delegate = self
    }

    private func initViewModel() {
        if !isViewModelSet {
            viewModel = PopularViewModel()
        }
    }

    internal func fetchPopular(page: Int = 1) {
        if isViewModelSet {
            fetchingMore = true
            MovieManager.sharedInstance().fetchMovies(endPoint: Constants.popularEndPoint, page: page) { [weak self] (result) in
                guard let weakSelf = self else { return }

                switch result {
                case .failure(let error):
                    print("[ERROR] Ops! \(String(describing: error))")
                    weakSelf.showAlertView(title: "Ops!", message: String(describing: error))

                case .success(let data):
                    DispatchQueue.main.async {
                        weakSelf.viewModel?.setMovieResult(data)

                        if weakSelf.viewModel?.getMoviesCount() ?? 0 == 0 {
                            weakSelf.viewModel?.setMovies(data.results)
                        } else {
                            weakSelf.viewModel?.updateMovies(data.results)
                        }

                        weakSelf.viewModel?.setPagination(data)
                        weakSelf.fetchingMore = false
                    }
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
