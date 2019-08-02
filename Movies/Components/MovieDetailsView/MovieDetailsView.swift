//
//  MovieDetailsView.swift
//  Movies
//
//  Created by Piero Mattos on 27/07/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import AVFoundation
import AVKit
import UIKit
import WebKit

class MovieDetailsView: UIScrollView {

    // MARK: - Properties

    @IBOutlet var movieDetailsCollection: [UIView]!
    @IBOutlet weak var contentStackView: UIStackView!
    @IBOutlet weak var loadingIndicator: UIActivityIndicatorView!
    @IBOutlet weak var errorView: UIStackView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var releaseDateLabel: UILabel!
    @IBOutlet weak var durationLabel: UILabel!
    @IBOutlet weak var genresLabel: UILabel!
    @IBOutlet weak var posterImageContainer: UIView!
    @IBOutlet weak var posterImageView: UIImageView!
    @IBOutlet weak var overviewLabel: UILabel!
    @IBOutlet weak var castContainerView: UIView!
    @IBOutlet var trailerSectionViews: [UIView]!
    @IBOutlet weak var trailerContainerView: UIView!

    fileprivate var state: State = .loading
    fileprivate var movieDetails: MovieDetails?

    var movie: Movie? {
        didSet {
            guard let movie = movie else { return }
            loadDetails(forMovie: movie)
        }
    }

    var didTapPosterHandler: (() -> Void)?

    // MARK: - Methods

    fileprivate func loadDetails(forMovie movie: Movie) {
        state = .loading
        updateView()

        MovieDetails.load(forMovie: movie) { [weak self] movieDetails, error in
            guard let strongSelf = self else { return }

            guard
                error == nil,
                let movieDetails = movieDetails
                else {
                    strongSelf.state = .error
                    strongSelf.updateView()
                    return
            }
            strongSelf.state = .displayingDetails
            strongSelf.movieDetails = movieDetails
            strongSelf.updateView()
        }
    }

    fileprivate func updateView() {
        DispatchQueue.main.async { [weak self] in
            guard let strongSelf = self else { return }

            switch strongSelf.state {

            case .loading:
                strongSelf.movieDetailsCollection.forEach({ $0.isHidden = true })
                strongSelf.errorView.isHidden = true
                strongSelf.loadingIndicator.startAnimating()

            case .error:
                strongSelf.movieDetailsCollection.forEach({ $0.isHidden = true })
                strongSelf.errorView.isHidden = false
                strongSelf.loadingIndicator.stopAnimating()

            case .displayingDetails:
                strongSelf.movieDetailsCollection.forEach({ $0.isHidden = false })
                strongSelf.errorView.isHidden = true
                strongSelf.loadingIndicator.stopAnimating()
                strongSelf.populateDetails()
            }
        }
    }

    fileprivate func populateDetails() {
        guard let movie = movie, let details = movieDetails else { return }

        setupInfo(movie, details)
        setupPoster(movie)
        setupCast(details)
        setupTrailer(details)

        layoutIfNeeded()
        contentSize = CGSize(width: frame.size.width, height: contentStackView.frame.size.height + 40)
    }

    fileprivate func setupInfo(_ movie: Movie, _ details: MovieDetails) {
        titleLabel.text = movie.title
        releaseDateLabel.text = "Release date: \(movie.releaseDateString)"
        durationLabel.text = "Duration: \(details.runtime) minutes"
        genresLabel.text = details.genres.map({ $0.name }).joined(separator: ", ")
        overviewLabel.text = details.overview
    }

    fileprivate func setupPoster(_ movie: Movie) {
        posterImageView.loadImage(movie.posterURL)

        let tapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(didTapPoster))
        posterImageContainer.addGestureRecognizer(tapGestureRecognizer)
    }

    fileprivate func setupCast(_ details: MovieDetails) {
        let castContainerHeight = castContainerView.frame.height
        let castViews = details.cast.map({ return PersonView.forPerson($0) })

        let castStackView = UIStackView(arrangedSubviews: castViews)
        castStackView.alignment = .fill
        castStackView.axis = .horizontal
        castStackView.distribution = .fillEqually
        castStackView.bounds.size = CGSize(width: CGFloat(castViews.count) * castContainerHeight, height: castContainerHeight)
        castStackView.frame.origin = .zero

        let castScrollView = UIScrollView()
        castScrollView.contentSize = castStackView.bounds.size
        castScrollView.addSubview(castStackView)
        castScrollView.contentOffset = .zero

        castContainerView.addSubview(castScrollView)
        castScrollView.constrainToSuperview()
    }

    fileprivate func setupTrailer(_ details: MovieDetails) {
        let youtubeTrailers = details.videos.filter({ $0.isYouTube && $0.isTrailer })

        if let trailerUrl = youtubeTrailers.first?.youTubeURL {
            let webView = WKWebView()
            webView.load(URLRequest(url: trailerUrl))
            trailerContainerView.addSubview(webView)
            webView.constrainToSuperview()
        } else {
            trailerSectionViews.forEach({ $0.isHidden = true })
        }
    }

    @objc fileprivate func didTapPoster() {
        didTapPosterHandler?()
    }

    @IBAction func didTapViewOnIMDB(_ sender: Any) {
        guard
            let imdbId = movieDetails?.imdbId,
            let imdbURL = URL(string: "https://www.imdb.com/title/\(imdbId)")
            else { return }
        DispatchQueue.main.async { UIApplication.shared.open(imdbURL) }
    }

    // MARK: - Enums

    fileprivate enum State {
        case loading
        case error
        case displayingDetails
    }
}
