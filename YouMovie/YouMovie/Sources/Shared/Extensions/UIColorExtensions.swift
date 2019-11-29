//
//  UIColorExtensions.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

extension UIColor {

    // MARK: - Definitions

    struct Style {
        static var darkGreenBackground: UIColor { return UIColor(hexadecimal: 0x0C1B21) }
        static var darkGreen: UIColor { return UIColor(hexadecimal: 0x29442B) }
        static var lightGreen: UIColor { return UIColor(hexadecimal: 0x63CC82) }
        static var darkYellow: UIColor { return UIColor(hexadecimal: 0x413D17) }
        static var lightYellow: UIColor { return UIColor(hexadecimal: 0xD3D353) }
        static var darkRed: UIColor { return UIColor(hexadecimal: 0x501A34) }
        static var lightRed: UIColor { return UIColor(hexadecimal: 0xCA3961) }
        static var darkGrayCustom: UIColor { return UIColor(hexadecimal: 0x666666) }
        
        static var blackAdaptative: UIColor {
            if #available(iOS 13.0, *) {
                return .label
            } else {
                return .black
            }
        }
        
        static var whiteAdaptative: UIColor {
            if #available(iOS 13.0, *) {
                return .systemBackground
            } else {
                return .white
            }
        }
        
        static var darkGrayAdaptative: UIColor {
            if #available(iOS 13.0, *) {
                return .secondaryLabel
            } else {
                return .darkGray
            }
        }
    }

    // MARK: - Inits

    convenience init(hexadecimal: Int, alpha: CGFloat = 1.0) {
        let red = CGFloat((hexadecimal >> 16) & 0xff)
        let green = CGFloat((hexadecimal >> 8) & 0xff)
        let blue = CGFloat(hexadecimal & 0xff)

        self.init(red: red / 255.0, green: green / 255.0, blue: blue / 255.0, alpha: alpha)
    }
}
