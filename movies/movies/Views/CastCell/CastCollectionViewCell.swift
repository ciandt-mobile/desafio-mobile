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
    @IBOutlet weak var characterNameLabel: UILabel!

    private let movieService = MovieDbService()
    static let viewIdentifier: String = "CastCollectionViewCell"

    override func awakeFromNib() {
        super.awakeFromNib()
    }

    func setActor(_ actor: Actor) {

        self.actorNameLabel.text = actor.name
        self.characterNameLabel.text = actor.character

        guard let profileImagePath = actor.profile_path else {
            return
        }

        movieService.downloadImage(imagePath: profileImagePath, imageResolution: .low) { profileImage in
            self.actorProfileImage.image = profileImage
            self.actorProfileImage.backgroundColor = nil
        }
    }
}
