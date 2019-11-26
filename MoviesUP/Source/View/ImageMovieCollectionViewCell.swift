//
//  ImageMovieCollectionViewCell.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 23/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import UIKit

class ImageMovieCollectionViewCell: UICollectionViewCell {

    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var dateLabel: UILabel! {
        didSet{
            self.dateLabel.layer.borderWidth = 1
            self.dateLabel.layer.borderColor = UIColor.white.cgColor
        }
    }
    @IBOutlet weak var titleLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
    
    override func layoutIfNeeded() {
        self.layoutIfNeeded()
    }
}
