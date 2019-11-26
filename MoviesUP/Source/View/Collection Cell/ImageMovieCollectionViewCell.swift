//
//  ImageMovieCollectionViewCell.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 23/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import UIKit

class ImageMovieCollectionViewCell: UICollectionViewCell {

    @IBOutlet weak var imageView: UIImageView!{
        didSet{
            self.imageView.addGradientBackground(firstColor: .clear, secondColor: .black)
        }
    }
    @IBOutlet weak var dateLabel: UILabel! {
        didSet{
            self.dateLabel.layer.borderWidth = 1
            self.dateLabel.layer.borderColor = UIColor.white.cgColor
            self.dateLabel.layer.cornerRadius = 5
        }
    }
    @IBOutlet weak var titleLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
}

extension UIView{
    func addGradientBackground(firstColor: UIColor, secondColor: UIColor){
        clipsToBounds = true
        let gradientLayer = CAGradientLayer()
        gradientLayer.colors = [firstColor.cgColor, secondColor.cgColor]
        gradientLayer.frame = self.bounds
        gradientLayer.startPoint = CGPoint(x: 0, y: 0)
        gradientLayer.endPoint = CGPoint(x: 0, y: 1)
        print(gradientLayer.frame)
        self.layer.insertSublayer(gradientLayer, at: 0)
    }
}
