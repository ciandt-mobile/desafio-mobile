//
//  MovieDetailsViewController.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 30/11/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import UIKit

class MovieDetailsViewController: CustomViewController, Identifiable {

    @IBOutlet weak var castCollectionView: UICollectionView!
    @IBOutlet weak var posterImg: CustomImageView!
    @IBOutlet weak var movieTitleLabel: UILabel!
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var genresLabel: UILabel!
    @IBOutlet weak var movieDescription: UITextView!
    

    internal var collectionViewFlowLayout: UICollectionViewFlowLayout!
    var viewModel: MovieDetailsViewModel? {
        didSet {
            setupViewModel()
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        setupCollectionView()
        initGenres()
        registerNib()
        fetchCast()
        removeBackBarButtonItemText()
        setNavigationBarBackgroundTransparent()
        setupUI()
    }

    private func setupUI() {
        var voteAverageAndGenres: Double {
            guard let voteAverageAndGenres = viewModel?.getMovieDetails()?.voteAverage else { return 0 }
            return voteAverageAndGenres
        }

        var genresList: String {
            guard let genresList = viewModel?.mapGenres() else { return "" }
            return genresList
        }

        posterImg.downloadFrom(stringURL: viewModel?.getMovieDetails()?.posterPath ?? Constants.detaultImageSize512)
        movieTitleLabel.text = viewModel?.getMovieDetails()?.title
        dateLabel.text = Formatter.getYear(dateString: viewModel?.getMovieDetails()?.releaseDate ?? "1900")
        genresLabel.text = "\(voteAverageAndGenres) | " + genresList
        movieDescription.text = viewModel?.getMovieDetails()?.overview
    }

    private func setupViewModel() {
        self.viewModel?.delegate = self
    }

    private func initGenres() {
        if GenresManager.sharedInstance().getGenres().count > 0 {
            viewModel?.setGenres(GenresManager.sharedInstance().getGenres())
        } else {
            GenresManager.sharedInstance().fetchGenres(endPoint: Constants.genreEndPoint)
            initGenres()
        }
    }

    override func viewWillLayoutSubviews() {
        super.viewWillLayoutSubviews()
        setupCollectionViewItemSize()
    }

    internal func fetchCast(page: Int = 1) {
        guard let movieId = viewModel?.getMovieDetails()?.id else { return }

        CastManager.sharedInstance().fetchCast(endPoint: Constants.castEndPoint, movieId: movieId) { [weak self] (result) in
            guard let weakSelf = self else { return }
            switch result {
            case .failure(let error):
                print("[ERROR] Ops! \(String(describing: error))")
                weakSelf.showAlertView(title: "Ops!", message: String(describing: error))

            case .success(let data):
                DispatchQueue.main.async {
                    weakSelf.viewModel?.setCastCrew(cast: data)
                }
            }
        }

    }

}
