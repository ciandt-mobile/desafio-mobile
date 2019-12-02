//
//  UIImageView.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 30/11/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import UIKit

extension UIImageView {

    func roundedAllCorners(width: Int = 3, height: Int = 3) {
        let maskPath1 = UIBezierPath(
            roundedRect: bounds,
            byRoundingCorners: UIRectCorner.allCorners,
            cornerRadii: CGSize(width: width, height: height)
        )

        let maskLayer1 = CAShapeLayer()
        maskLayer1.frame = bounds
        maskLayer1.path = maskPath1.cgPath
        layer.mask = maskLayer1
        layer.masksToBounds = true
        self.clipsToBounds = true
    }

    func roundedTopCorners(width: Int = 3, height: Int = 3){
        let maskPath1 = UIBezierPath(
            roundedRect: bounds,
            byRoundingCorners: [.topLeft , .topRight],
            cornerRadii: CGSize(width: width, height: height)
        )

        let maskLayer1 = CAShapeLayer()
        maskLayer1.frame = bounds
        maskLayer1.path = maskPath1.cgPath
        layer.mask = maskLayer1
    }

}
