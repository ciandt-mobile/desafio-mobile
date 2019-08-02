//
//  MovieCell.swift
//  Movies
//
//  Created by Piero Mattos on 27/07/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import UIKit

class MovieCell: UICollectionViewCell {

    // MARK: - Properties

    @IBOutlet weak var posterImageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var releaseDateLabel: UILabel!

    var movie: Movie? {
        didSet {
            guard let movie = movie else { return }
            titleLabel.text = movie.title
            releaseDateLabel.text = movie.releaseDateString
            posterImageView.loadImage(movie.posterURL)
        }
    }

    // MARK: - Methods

    override func awakeFromNib() {
        super.awakeFromNib()
        layer.shadowRadius = 20.0
        layer.shadowOpacity = 0.9
        layer.shadowOffset = .zero
        clipsToBounds = false
    }

    static func loadFromNib() -> MovieCell {
        guard
            let nib = Bundle.main.loadNibNamed("MovieCell", owner: nil, options: nil),
            let movieCell = nib[0] as? MovieCell
            else { fatalError("Could not load MovieCell from xib file.") }
        return movieCell
    }
}
