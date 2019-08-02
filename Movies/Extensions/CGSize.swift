//
//  CGSize.swift
//  Movies
//
//  Created by Piero Mattos on 01/08/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import UIKit

extension CGSize {

    mutating func scaleToFit(inside container: CGSize) {
        var scale: CGFloat = 1.0

        repeat {
            scale -= 0.01
            self = self.applying(CGAffineTransform(scaleX: scale, y: scale))
        } while (self.width > container.width || self.height > container.height)
    }
}
