//
//  UIDevice.swift
//  Movies
//
//  Created by Piero Mattos on 01/08/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import UIKit

extension UIDevice {
    var isInPortraitOrientation: Bool {
        switch UIDevice.current.orientation {
        case .landscapeLeft, .landscapeRight, .unknown:
            return false
        default:
            return true
        }
    }
}
