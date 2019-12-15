//
//  MovieCollectionViewCell.swift
//  MoviesChallenge
//
//  Created by Leonardo Bortolotti on 14/12/19.
//  Copyright © 2019 Leonardo Bortolotti. All rights reserved.
//

import UIKit

class MovieCollectionViewCell: UICollectionViewCell {

    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var dateLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        // Adiciona borda branca na Label de data
        dateLabel.layer.borderWidth = 1
        dateLabel.layer.borderColor = UIColor.white.cgColor
    }
    
}
