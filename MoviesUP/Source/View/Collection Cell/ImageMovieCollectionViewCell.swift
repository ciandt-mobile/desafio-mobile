//
//  ImageMovieCollectionViewCell.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 23/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import UIKit

class ImageMovieCollectionViewCell: UICollectionViewCell {

    @IBOutlet weak var imageView: UIImageView! {
        didSet{
            self.imageView.addGradientBackground(firstColor: .clear, secondColor: .black)
        }
    }
    @IBOutlet weak var viewDate: UIView! {
        didSet{
            self.viewDate.layer.borderWidth = 1
            self.viewDate.layer.borderColor = UIColor.white.cgColor
            self.viewDate.layer.cornerRadius = 5
        }
    }
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var titleLabel: UILabel!
}
