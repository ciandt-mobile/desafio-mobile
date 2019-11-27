//
//  RecommendationCollectionViewCell.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

class RecommendationCollectionViewCell: UICollectionViewCell {

    // MARK: - Outlets

    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!

    // MARK: - Internal Properties

    static let size: CGSize = CGSize(width: 220.0, height: 200.0)

    // MARK: - Lifecycle

    override func awakeFromNib() {
        super.awakeFromNib()
        self.setupCommon()
    }

    override func prepareForReuse() {
        super.awakeFromNib()
        self.setupCommon()
    }

    // MARK: - Internal Methods

    func setupUI(with recommendation: MovieEntity) {

        self.titleLabel.text = recommendation.title

        if let backdropPathURL = recommendation.backdropPathURL {
            let urlString = APIRoutes.MovieDetails.fetchImage(fromPath: backdropPathURL, size: .w780)
            self.imageView.downloadImage(fromURLString: urlString)
        }
    }

    // MARK: - Private Methods

    private func setupCommon() {
        self.titleLabel.text = nil
        self.imageView.image = nil
        self.imageView.cancelDownloadImage()
    }
}
