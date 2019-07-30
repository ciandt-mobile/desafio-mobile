//
//  FavoriteMovieTableViewCell.swift
//  TheMovieApp
//
//  Created by Wilson Kim on 28/06/19.
//  Copyright © 2019 WilsonKim. All rights reserved.
//

import UIKit

class FavoriteMovieTableViewCell: UITableViewCell {

    @IBOutlet weak var containerView: UIView!
    @IBOutlet weak var movieTitleLabel: UILabel!
    @IBOutlet weak var releaseYearLabel: UILabel!
    @IBOutlet weak var overviewLabel: UILabel!
    @IBOutlet weak var movieImageView: UIImageView!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        setupContainerView()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    private func setupContainerView() {
        containerView.layer.cornerRadius = 4
    }
    
    public func setMovieInfo(_ movie:MovieViewModel) {
        movieTitleLabel.text = movie.title
        releaseYearLabel.text = "\(movie.releaseDate.getYearValue())"
        overviewLabel.text = movie.overview
        movieImageView.loadImageFrom(path: movie.posterPath)
    }
}
