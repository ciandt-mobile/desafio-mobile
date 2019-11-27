//
//  MovieCollectionViewCell.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

class MovieCollectionViewCell: UICollectionViewCell {

    // MARK: - Outlets

    @IBOutlet weak var backgroundImageView: UIImageView!
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!

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

    func setupUI(with movie: MovieEntity) {

        self.titleLabel.text = movie.title

        if let movieImagePath = movie.posterPathURL ?? movie.backdropPathURL {
            let urlString = APIRoutes.Movies.fetchImage(fromPath: movieImagePath, size: .w500)
            self.imageView.downloadImage(fromURLString: urlString) { [weak self] in
                self?.backgroundImageView.image = self?.imageView.image
            }
        }
    }

    // MARK: - Private Methods

    private func setupCommon() {
        self.titleLabel.text = nil
        self.backgroundImageView.image = nil
        self.imageView.image = nil
        self.imageView.cancelDownloadImage()
    }
}
