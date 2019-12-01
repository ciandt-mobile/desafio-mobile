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

    let movieService = MovieDbService()
    var movie: Movie?

    static let viewIdentifier: String = "PopularMoviesTableTableViewCell"

    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }

    func setMovie(_ movie: Movie) {

        self.movieTitle.text = movie.title
        self.voteAverageScoreLabel.text = String(movie.vote_average)

        if let releaseDateComponents = movie.releaseDateComponents, let releaseDate = Calendar.current.date(from: releaseDateComponents) {
            let dateFormatter = DateFormatter()
            dateFormatter.dateFormat = Constants.APP_DATE_FORMAT

            self.yearLabel.text = dateFormatter.string(from: releaseDate)
        }

        if let posterPath = movie.poster_path {
            self.movieService.downloadImage(imagePath: posterPath, imageResolution: .medium) { posterImage in
                self.moviePoster.image = posterImage
                self.moviePoster.backgroundColor = nil
            }
        }

        self.movie = movie
    }
}
