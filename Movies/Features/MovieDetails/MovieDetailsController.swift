//
//  MovieDetailsController.swift
//  Movies
//
//  Created by Piero Mattos on 31/07/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import UIKit

class MovieDetailsController: UIViewController {

    // MARK: - Properties

    private var movieDetailsView: MovieDetailsView!

    var movie: Movie? {
        didSet { movieDetailsView.movie = movie }
    }

    // MARK: - Lifecycle

    override func viewDidLoad() {
        super.viewDidLoad()

        guard
            let nib = Bundle.main.loadNibNamed("MovieDetailsView", owner: self, options: nil),
            let detailsView = nib[0] as? MovieDetailsView
            else { fatalError("Could not load MovieDetailsView nib file.") }

        movieDetailsView = detailsView
        movieDetailsView.didTapPosterHandler = { [weak self] in
            guard
                let strongSelf = self,
                let movie = strongSelf.movie,
                let posterVC = UIStoryboard(name: "Poster", bundle: Bundle.main).instantiateViewController(withIdentifier: "PosterController") as? PosterController
                else { return }

            _ = posterVC.view
            posterVC.posterImageView.loadImage(movie.posterURL)
            DispatchQueue.main.async { [weak self] in
                self?.navigationController?.pushViewController(posterVC, animated: true)
            }
        }

        movieDetailsView.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(movieDetailsView)
        view.addConstraints([
            movieDetailsView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
            movieDetailsView.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor),
            movieDetailsView.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor),
            movieDetailsView.bottomAnchor.constraint(equalTo: view.bottomAnchor)
        ])
    }

    // MARK: - Methods

    @IBAction func didTapDone(_ sender: Any) {
        DispatchQueue.main.async { [weak self] in
            self?.navigationController?.dismiss(animated: true, completion: nil)
        }
    }
}
