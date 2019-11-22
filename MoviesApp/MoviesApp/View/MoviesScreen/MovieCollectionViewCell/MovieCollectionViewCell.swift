//
//  MovieCollectionViewCell.swift
//  MoviesApp
//
//  Created by Luan Audibert on 11/21/19.
//  Copyright © 2019 Luan Audibert. All rights reserved.
//

import UIKit
import Kingfisher
import RxSwift
import RxCocoa

class MovieCollectionViewCell: UICollectionViewCell {

    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var dateLabel: PaddingLabel!
    @IBOutlet weak var movieNameLabel: UILabel!

    let dateFormatter = Formatter.moviesFormatter
    override func awakeFromNib() {
        super.awakeFromNib()
        self.dateLabel.layer.borderWidth = 1.5
        self.dateLabel.layer.borderColor = UIColor.white.cgColor
        self.imageView.kf.indicatorType = .activity
    }
    
    func setDate(_ date: Date) {
        self.dateLabel.text = dateFormatter.string(from: date)
    }
    
    func setImage(imageUrl: String) {
        guard let url = URL(string: "https://image.tmdb.org/t/p/original\(imageUrl)") else { return }
        self.imageView.kf.setImage(with: url, options: [.transition(.fade(0.2))])
    }
}
