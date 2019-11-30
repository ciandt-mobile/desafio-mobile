//
//  MovieDetailsViewController.swift
//  movies
//
//  Created by Arthur Silva on 11/30/19.
//  Copyright © 2019 Arthur Silva. All rights reserved.
//

import UIKit

class MovieDetailsViewController: UIViewController {

    @IBOutlet weak var backdropImage: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var overviewTextView: UITextView!
    @IBOutlet weak var overviewTextViewHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var movieDetailsLabel: UILabel!

    static let viewIdentifier: String = "MovieDetailsViewController"
    private let movieService = MovieDbService()
    var movie: Movie?
    var movieDetails: MovieDetails?

    override func viewDidLoad() {
        super.viewDidLoad()

        guard let movie = self.movie else {
            return
        }

        movieService.getMovieDetails(movieId: movie.id, onSuccess: setMovieDetails) {
            //FAILURE
        }

        self.titleLabel.text = movie.title
        self.overviewTextView.text = movie.overview
        adjustTextViewHeight()

        if let releaseDateComponents = movie.releaseDateComponents, let year = releaseDateComponents.year {
            self.movieDetailsLabel.text = String(year)
        }

        movieService.downloadImage(imagePath: movie.backdrop_path, imageType: .backdrop) { image in
            self.backdropImage.image = image
        }
    }

    func adjustTextViewHeight() {
        // BUG: this code is not calculating effectivelly the text height
        let heightAddition: CGFloat = 20.0

        let textViewSize = self.overviewTextView.visibleSize
        let sizeThatFitsText = self.overviewTextView.sizeThatFits(CGSize(width: textViewSize.width, height: CGFloat.greatestFiniteMagnitude))
        self.overviewTextViewHeightConstraint.constant = sizeThatFitsText.height + heightAddition
    }

    func setMovieDetails(movieDetails: MovieDetails) {
        self.movieDetails = movieDetails

        var genres: String = ""
        for (index, genre) in movieDetails.genres.enumerated() {
            if index != 0 {
                genres += ", "
            }
            genres += genre.name
        }

        let details: String = " | \(movieDetails.runtime)min | \(movieDetails.status) | \(genres)"
        self.movieDetailsLabel.text! += details
    }
}
