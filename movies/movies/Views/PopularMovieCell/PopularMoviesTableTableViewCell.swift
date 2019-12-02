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
    @IBOutlet weak var daysUntilReleaseLabel: UILabel!

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
        self.movie = movie

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

        setDaysUntilRelease()
    }

    func setDaysUntilRelease() {
        guard let movie = self.movie else {
            return
        }

        let today = Date()
        if let releaseDateComponents = movie.releaseDateComponents, let releaseDate = Calendar.current.date(from: releaseDateComponents) {
            if releaseDate > today {
                if let numberOfDays = Calendar.current.dateComponents([.day], from: today, to: releaseDate).day {
                    self.daysUntilReleaseLabel.isHidden = false
                    var daysUntilReleaseText: String

                    if numberOfDays == 1 {
                        daysUntilReleaseText = Constants.RELEASE_TOMORROW
                    }
                    else {
                        daysUntilReleaseText = String(format: Constants.DAYS_UNTIL_RELEASE, numberOfDays)
                    }

                    self.daysUntilReleaseLabel.text = daysUntilReleaseText
                }
            }
            else {
                self.daysUntilReleaseLabel.isHidden = true
            }
        }
    }
}
