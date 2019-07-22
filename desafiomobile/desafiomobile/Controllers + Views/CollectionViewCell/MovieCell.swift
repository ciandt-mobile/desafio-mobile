//
//  MovieCell.swift
//  desafiomobile
//
//  Created by Erasmo Oliveira on 21/07/19.
//  Copyright © 2019 Erasmo Oliveira. All rights reserved.
//

import UIKit
import Kingfisher

class MovieCell: UICollectionViewCell {

    @IBOutlet weak var image: UIImageView!
    @IBOutlet weak var date: UILabel!
    @IBOutlet weak var name: UILabel!
    
    func config(with movie: MovieDetail?) {
        
        guard let movie = movie else { return }
        
        if let imageString = movie.imageString {
        
            let imagePath = "https://image.tmdb.org/t/p/w500" + imageString
            if let imageUrl = URL(string: imagePath) {
                self.image.kf.setImage(with: imageUrl)
            }
            
        }
        
        date.text = movie.releaseDate
        name.text = movie.name
        
    }

}
