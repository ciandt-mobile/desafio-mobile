//
//  CastCrewCollectionViewCell.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

class CastCrewCollectionViewCell: UICollectionViewCell {

    // MARK: - Outlets

    @IBOutlet weak var imageView: CircularImageView!
    @IBOutlet weak var initialsLabel: UILabel!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var subtitleLabel: UILabel!

    // MARK: - Internal Properties

    static let size: CGSize = CGSize(width: 100.0, height: 200.0)

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

    func setupUI(with dataSource: CastCrewDataSource) {

        self.titleLabel.text = dataSource.title
        self.subtitleLabel.text = dataSource.subtitle

        if dataSource.profilePathURL.isEmpty {
            self.initialsLabel.isHidden = false
            self.initialsLabel.text = dataSource.title.split(separator: " ").compactMap({ $0.prefix(1) }).joined()
        } else {
            self.initialsLabel.isHidden = true
            let urlString = APIRoutes.MovieDetails.fetchImage(fromPath: dataSource.profilePathURL, size: .h632)
            self.imageView.downloadImage(fromURLString: urlString)
        }
    }

    // MARK: - Private Methods

    private func setupCommon() {
        self.titleLabel.text = nil
        self.subtitleLabel.text = nil
        self.imageView.image = nil
        self.imageView.cancelDownloadImage()
    }
}
