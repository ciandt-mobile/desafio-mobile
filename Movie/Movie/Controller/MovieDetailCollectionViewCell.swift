//
//  MovieDetailCollectionViewCell.swift
//  Movie
//
//  Created by Gabriel Guerrero on 26/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import UIKit
import Kingfisher

class MovieDetailCollectionViewCell: UICollectionViewCell {
    
    //MARK: - @IBOutlet's
    @IBOutlet weak var backgroundImageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var subTitleLabel: UILabel!
    
    func setupCell(with detail: MovieDetail?, and indexPath: IndexPath) {
        guard let detail = detail else {
            return
        }
        
        if indexPath.row == 0 {
            titleLabel.isHidden = true
            subTitleLabel.isHidden = true
            
            backgroundImageView.kf.indicatorType = .activity
            backgroundImageView.kf.setImage(with: URL(string: HTTP.baseImageURL185 + (detail.poster_path ?? "")))
        } else {
            titleLabel.isHidden = false
            subTitleLabel.isHidden = false
            
            if let cast = detail.credits?.cast?[indexPath.row - 1] {
                backgroundImageView.kf.indicatorType = .activity
                backgroundImageView.kf.setImage(with: URL(string: HTTP.baseImageURL185 + (cast.profile_path ?? "")))
                
                titleLabel.text = cast.name
                subTitleLabel.text = cast.character
            }
        }
    }
}
