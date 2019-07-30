//
//  MovieListCollectionViewCell.swift
//  TheMovieApp
//
//  Created by Wilson Kim on 24/06/19.
//  Copyright © 2019 WilsonKim. All rights reserved.
//

import UIKit
import Kingfisher

protocol MovieListCellDelegate {
    func didFavoriteMovie(_ cell: MovieListCollectionViewCell)
}

class MovieListCollectionViewCell: UICollectionViewCell {

    @IBOutlet weak var containerView: UIView!
    @IBOutlet private weak var mainImageView: UIImageView!
    @IBOutlet private weak var movieNameLabel: UILabel!
    @IBOutlet private weak var favoriteButton: FavoriteButton!
    @IBOutlet private weak var labelContainer: UIView!
    
    var delegate:MovieListCellDelegate?
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        setupLabel()
        setupBorder()
    }

    @IBAction func favoriteMovieButtonClicked(_ sender: Any) {
        self.delegate?.didFavoriteMovie(self)
    }
    
    private func setupLabel() {
        movieNameLabel.textColor = Colors.yellow
        labelContainer.backgroundColor = Colors.darkGray
    }
    
    private func setupBorder() {
        containerView.layer.borderWidth = 0.5
        containerView.layer.borderColor = Colors.darkGray.cgColor
        containerView.layer.cornerRadius = 4
    }
    
    public func setMovieCell(_ movie:MovieViewModel) {
        movieNameLabel.text = movie.title
        mainImageView.loadImageFrom(path: movie.posterPath)
    }
    
    public func setFavoriteButtonSelection(_ isSelected:Bool) {
        favoriteButton.isSelected = isSelected
    }
}
