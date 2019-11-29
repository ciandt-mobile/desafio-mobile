//
//  StringExtensions.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

// MARK: - String

extension String {

    func attributed(with font: UIFont, color: UIColor) -> NSAttributedString {
        let attributes: [NSAttributedString.Key: Any] = [.font: font, .foregroundColor: color]
        return NSAttributedString(string: self, attributes: attributes)
    }
}

// MARK: - NSAttributedString

extension NSAttributedString {

    func concatene(to attributedString: NSAttributedString) -> NSAttributedString {
        let current = NSMutableAttributedString(attributedString: self)
        current.append(attributedString)
        return current
    }
}
