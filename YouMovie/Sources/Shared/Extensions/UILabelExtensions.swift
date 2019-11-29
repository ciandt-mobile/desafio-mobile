//
//  UILabelExtensions.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

extension UILabel {

    func setLineSpacing(value: CGFloat) {

        let attributedString = NSMutableAttributedString(string: self.text ?? "")
        let paragraphStyle = NSMutableParagraphStyle()

        paragraphStyle.lineSpacing = value
        attributedString.addAttribute(NSAttributedString.Key.paragraphStyle,
                                      value: paragraphStyle,
                                      range: NSRange(location: 0, length: attributedString.length))

        self.attributedText = attributedString
    }
}
