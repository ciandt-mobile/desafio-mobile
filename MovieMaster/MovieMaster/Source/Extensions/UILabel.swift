//
//  UILabel.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 30/11/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import UIKit

extension UILabel {

    func setRatingBadge(_ rating: Double) {
        self.layer.cornerRadius = 3
        self.layer.borderWidth = 1
        self.layer.masksToBounds = true
        
        if rating > 5 && rating <= 10 {
            self.backgroundColor = #colorLiteral(red: 0, green: 0.648413837, blue: 0, alpha: 1)
            self.layer.borderColor = #colorLiteral(red: 0, green: 0.648413837, blue: 0, alpha: 1)
        } else if rating > 3 && rating <= 5 {
            self.backgroundColor = #colorLiteral(red: 0.994023025, green: 0.5723339319, blue: 0.1447838247, alpha: 1)
            self.layer.borderColor = #colorLiteral(red: 0.994023025, green: 0.5723339319, blue: 0.1447838247, alpha: 1)
        } else if rating >= 0 && rating <= 3 {
            self.backgroundColor = #colorLiteral(red: 0.521568656, green: 0.1098039225, blue: 0.05098039284, alpha: 1)
            self.layer.borderColor = #colorLiteral(red: 0.521568656, green: 0.1098039225, blue: 0.05098039284, alpha: 1)
        }
        self.text = "\(rating)"
    }
}

@IBDesignable class PaddingLabel: UILabel {
    
    @IBInspectable var topInset: CGFloat = 5.0
    @IBInspectable var bottomInset: CGFloat = 5.0
    @IBInspectable var leftInset: CGFloat = 16.0
    @IBInspectable var rightInset: CGFloat = 16.0
    
    override func drawText(in rect: CGRect) {
        let insets = UIEdgeInsets.init(top: topInset, left: leftInset, bottom: bottomInset, right: rightInset)
        super.drawText(in: rect.inset(by: insets))
    }
    
    override var intrinsicContentSize: CGSize {
        let size = super.intrinsicContentSize
        return CGSize(width: size.width + leftInset + rightInset,
                      height: size.height + topInset + bottomInset)
    }
}
