//
//  UIView.swift
//  Movies
//
//  Created by Piero Mattos on 27/07/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import UIKit

extension UIView {

    func constrainToSuperview(withPaddingOf padding: CGFloat = 0) {
        guard let superview = self.superview else { return }
        translatesAutoresizingMaskIntoConstraints = false
        superview.addConstraints([
            topAnchor.constraint(equalTo: superview.topAnchor, constant: padding),
            leadingAnchor.constraint(equalTo: superview.leadingAnchor, constant: padding),
            trailingAnchor.constraint(equalTo: superview.trailingAnchor, constant: -padding),
            bottomAnchor.constraint(equalTo: superview.bottomAnchor, constant: -padding)
        ])
    }
}
