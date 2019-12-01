//
//  CastCollectionViewCell.swift
//  movies
//
//  Created by Arthur Silva on 11/30/19.
//  Copyright © 2019 Arthur Silva. All rights reserved.
//

import UIKit

class CastCollectionViewCell: UICollectionViewCell {

    @IBOutlet weak var actorProfileImage: UIImageView!
    @IBOutlet weak var actorNameLabel: UILabel!

    private let movieService = MovieDbService()
    static let viewIdentifier: String = "CastCollectionViewCell"

    override func awakeFromNib() {
        super.awakeFromNib()
    }

    func setActor(_ actor: Cast) {
        self.actorNameLabel.text = actor.name

        movieService.downloadImage(imagePath: actor.profile_path, imageResolution: .low) { profileImage in
            self.actorProfileImage.image = profileImage
        }
    }
}
