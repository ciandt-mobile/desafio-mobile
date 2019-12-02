//
//  CastCollectionViewCell.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 01/12/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import UIKit

class CastCollectionViewCell: UICollectionViewCell {

    @IBOutlet weak var posterImg: CustomImageView!
    @IBOutlet weak var character: UILabel!
    @IBOutlet weak var name: UILabel!

    var cast: Cast? {
        didSet {
            if let castCrew = cast {
                setupUI(castCrew)
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
    
    internal func setupUI(_ data: Cast) {
        self.posterImg.downloadFrom(stringURL: data.profilePath ?? Constants.defaultImage)
        self.character.text = data.character
        self.name.text = data.name
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        self.posterImg.image = UIImage()
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
    }

}
