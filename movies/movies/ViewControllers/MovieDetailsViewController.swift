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
    @IBOutlet weak var releaseDateLabel: UILabel!
    @IBOutlet weak var overviewTextView: UITextView!
    @IBOutlet weak var overviewTextViewHeightConstraint: NSLayoutConstraint!

    private let movieService = MovieDbService()
    var movie: Movie?

    override func viewDidLoad() {
        super.viewDidLoad()

        guard let movie = self.movie else {
            return
        }

        self.titleLabel.text = movie.title
        self.overviewTextView.text = movie.overview
        adjustTextViewHeight()

        if let releaseDateComponents = movie.releaseDateComponents, let releaseDate = Calendar.current.date(from: releaseDateComponents) {
            let dateFormatter = DateFormatter()
            dateFormatter.dateFormat = Constants.APP_DATE_FORMAT
            self.releaseDateLabel.text = dateFormatter.string(from: releaseDate)
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
}
