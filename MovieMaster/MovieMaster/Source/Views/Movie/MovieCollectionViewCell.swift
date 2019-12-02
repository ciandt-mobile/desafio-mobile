//
//  MovieCollectionViewCell.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 01/12/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import UIKit

class MovieCollectionViewCell: UICollectionViewCell {

    @IBOutlet weak var posterImg: CustomImageView!
    @IBOutlet weak var ratingLabel: UILabel!
    @IBOutlet weak var titleLabel: UILabel!

    var movieCover: Movie? {
        didSet {
            if let movie = movieCover {
                setupMovie(movie)
            }
        }
    }
    
    // MARK: - CollectionView Cell Functions
    override func awakeFromNib() {
        super.awakeFromNib()
        self.contentView.autoresizingMask.insert(.flexibleHeight)
        self.contentView.autoresizingMask.insert(.flexibleWidth)
        self.posterImg.image = nil
    }
    
    internal func setupMovie(_ data: Movie) {
        self.posterImg.downloadFrom(stringURL: data.posterPath ?? Constants.defaultImage)
        self.titleLabel.text = data.title
        self.ratingLabel.setRatingBadge(data.voteAverage ?? 0.0)
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        self.posterImg.image = UIImage()
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
    }

}
