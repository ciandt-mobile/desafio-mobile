//
//  MoviesListCollectionViewCell.swift
//  Movie
//
//  Created by Gabriel Guerrero on 26/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import UIKit
import Kingfisher

class MoviesListCollectionViewCell: UICollectionViewCell {
    
    //MARK: - @IBOutlet's
    @IBOutlet weak var movieTitleLabel: UILabel!
    @IBOutlet weak var moviePosterImageView: UIImageView!
    @IBOutlet weak var movieReleaseDateLabel: PaddingLabel!
    
    //MARK: - Public Method's
    func setupMovieCell(with movie: Movies) {
        movieTitleLabel.text = movie.title
        
        movieReleaseDateLabel.text = movie.release_date?.convertDateFormater()
        
        moviePosterImageView.kf.indicatorType = .activity
        moviePosterImageView.kf.setImage(with: URL(string: HTTP.baseImageURL185 + (movie.poster_path ?? "")))
    }
}
