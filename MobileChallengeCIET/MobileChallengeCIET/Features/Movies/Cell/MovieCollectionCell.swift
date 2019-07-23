//
//  MovieCollectionCell.swift
//  MobileChallengeCIET
//
//  Created by Guilherme C Rosa on 22/07/19.
//  Copyright © 2019 Guilherme C Rosa. All rights reserved.
//

import UIKit

class MovieCollectionCell: UICollectionViewCell {

    @IBOutlet weak var imageBanner: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    func setup(movie: Movie) {
        self.imageBanner.contentMode = .scaleAspectFit
        self.serviceImage(movie: movie)
    }
    
    private func serviceImage(movie: Movie) {
        let service = TheMovieDBService()
        
        service.getMovieImage(movie: movie) { (image, error) in
            if let err = error {
                print("deu errado error")
                print(err)
            } else {
                if let img = image {
                    self.imageBanner.image = img
                } else {
                    print("deu errado image")
                }
            }
        }
        
    }
}
