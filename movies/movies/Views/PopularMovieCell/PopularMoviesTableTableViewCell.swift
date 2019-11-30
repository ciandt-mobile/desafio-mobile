//
//  PopularMoviesTableTableViewCell.swift
//  movies
//
//  Created by Arthur Silva on 11/30/19.
//  Copyright © 2019 Arthur Silva. All rights reserved.
//

import UIKit

class PopularMoviesTableTableViewCell: UITableViewCell {

    @IBOutlet weak var moviePoster: UIImageView!
    @IBOutlet weak var movieTitle: UILabel!
    @IBOutlet weak var yearLabel: UILabel!
    @IBOutlet weak var voteAverageScoreLabel: UILabel!
    @IBOutlet weak var genresView: UIStackView!

    let movieService = MovieDbService()
    var movie: Movie?

    static let cellIdentifier: String = "PopularMoviesTableTableViewCell"

    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }

    func setMovie(_ movie: Movie) {

        self.movieTitle.text = movie.title
        self.voteAverageScoreLabel.text = String(movie.vote_average)

        if let releaseDateComponents = movie.releaseDateComponents, let releaseYear = releaseDateComponents.year {
            self.yearLabel.text = String(releaseYear)
        }

        self.movieService.downloadImage(imagePath: movie.poster_path, imageType: .poster) { posterImage in
            self.moviePoster.image = posterImage
        }

        self.movie = movie
    }
}
