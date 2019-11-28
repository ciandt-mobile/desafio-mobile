//
//  UIViewExtensions.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

extension UIView {

    static var reuseIdentifier: String {
        return String(describing: self.self)
    }
}

// MARK: - Activity Indicator

extension UIView {

    func showActivityIndicator(at point: CGPoint? = nil,
                               isLarge: Bool = false,
                               color: UIColor? = nil) {

        DispatchQueue.main.async {
            guard !self.subviews.contains(where: { $0.isKind(of: UIActivityIndicatorView.self) }) else { return }

            let viewHeight = self.frame.height
            let viewWidth = self.frame.width
            let indicator = UIActivityIndicatorView()

            indicator.style = isLarge ? .whiteLarge : .white
            indicator.color = color ?? UIColor.lightGray
            indicator.center = CGPoint(x: point?.x ?? viewWidth / 2, y: point?.y ?? viewHeight / 2)
            indicator.startAnimating()

            self.addSubview(indicator)
        }
    }

    func hideActivityIndicator() {
        self.subviews.forEach({ (view) in
            if let indicator = view as? UIActivityIndicatorView {
                indicator.stopAnimating()
                indicator.removeFromSuperview()
            }
        })
    }
}
