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
    @IBOutlet weak var movieDetailsLabel: UILabel!
    @IBOutlet weak var taglineLabel: UILabel!
    @IBOutlet weak var overviewTextView: UITextView!
    @IBOutlet weak var taglineOverviewSeparatorView: UIView!
    @IBOutlet weak var overviewTextViewHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var castCollectionView: UICollectionView!

    static let viewIdentifier: String = "MovieDetailsViewController"
    private let movieService = MovieDbService()
    var movie: Movie?
    var movieDetails: MovieDetails?
    var movieCast: [Actor]?

    override func viewDidLoad() {
        super.viewDidLoad()

        getMovieData()
        setupView()
    }

    func getMovieData() {
        guard let movie = self.movie else {
            return
        }

        movieService.getMovieDetails(movieId: movie.id, onSuccess: setMovieDetails) {
            //FAILURE
        }

        movieService.getMovieCast(movieId: movie.id, onSuccess: setMovieCast) {
            // FAILURE
        }
    }

    func setupView() {
        guard let movie = self.movie else {
            return
        }

        let nib = UINib(nibName: CastCollectionViewCell.viewIdentifier, bundle: nil)
        self.castCollectionView.register(nib, forCellWithReuseIdentifier: CastCollectionViewCell.viewIdentifier)

        self.titleLabel.text = movie.title
        self.overviewTextView.text = movie.overview
        adjustTextViewHeight()

        if let releaseDateComponents = movie.releaseDateComponents, let year = releaseDateComponents.year {
            self.movieDetailsLabel.text = String(year)
        }

        movieService.downloadImage(imagePath: movie.backdrop_path, imageResolution: .high) { image in
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

        if movieDetails.tagline != "" {
            self.taglineLabel.text = movieDetails.tagline
            self.taglineLabel.isHidden = false
            self.taglineOverviewSeparatorView.isHidden = false
        }

    }

    func setMovieCast(cast: [Actor]) {
        self.movieCast = cast

        self.castCollectionView.reloadData()
    }
}

extension MovieDetailsViewController: UICollectionViewDataSource {

    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.movieCast?.count ?? 0
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {

        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: CastCollectionViewCell.viewIdentifier, for: indexPath) as? CastCollectionViewCell else {
            fatalError("Could not dequeue CastCollectionViewCell")
        }

        if let actor = self.movieCast?[indexPath.row] {
            cell.setActor(actor)
        }

        return cell
    }

}
