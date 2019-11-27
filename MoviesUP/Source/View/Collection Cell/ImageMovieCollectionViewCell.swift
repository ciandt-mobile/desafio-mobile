//
//  ImageMovieCollectionViewCell.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 23/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import UIKit

class ImageMovieCollectionViewCell: UICollectionViewCell {
    
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var viewDate: UIView! {
        didSet{
            self.viewDate.layer.borderWidth = 1
            self.viewDate.layer.borderColor = UIColor.white.cgColor
            self.viewDate.layer.cornerRadius = 5
        }
    }
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var titleLabel: UILabel!
    
    public let gradientLayer = CAGradientLayer()
    
    func addGradientLayer() {
        self.backgroundColor = .clear
        imageView.layer.addSublayer(self.gradientLayer)
    }
    
    func setGradient() {
        self.gradientLayer.colors = [UIColor.clear.cgColor, UIColor.black.cgColor]
        self.gradientLayer.startPoint = CGPoint(x: 0, y: 0)
        self.gradientLayer.endPoint = CGPoint (x: 0, y: 1.3)
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        setGradient()
        addGradientLayer()
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        self.gradientLayer.frame = self.bounds
    }
}
