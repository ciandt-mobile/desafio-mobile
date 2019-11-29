//
//  CircularImageView.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

@IBDesignable
class CircularImageView: UIImageView {

    // MARK: - Internal Properties

    @IBInspectable var isCircular: Bool = true {
        didSet {
            self.setupUI()
        }
    }

    @IBInspectable var cornerRadius: CGFloat = 10.0 {
        didSet {
            self.layer.cornerRadius = self.cornerRadius
        }
    }

    @IBInspectable var borderedWidth: CGFloat = 0.0 {
        didSet {
            self.layer.borderWidth = self.borderedWidth
        }
    }

    @IBInspectable var borderedColor: UIColor = .lightGray {
        didSet {
            self.layer.borderColor = self.borderedColor.cgColor
        }
    }

    // MARK: - Lifecycle

    override func layoutSubviews() {
        super.layoutSubviews()
        self.setupUI()
    }

    override func prepareForInterfaceBuilder() {
        super.prepareForInterfaceBuilder()
        self.setupUI()
    }

    override func awakeFromNib() {
        super.awakeFromNib()
        self.setupUI()
    }

    // MARK: - Private Methods

    private func setupUI() {
        self.layer.cornerRadius = self.isCircular ? self.frame.width / 2 : self.cornerRadius
        self.layer.borderWidth = self.borderedWidth
        self.layer.borderColor = self.borderedColor.cgColor
    }
}
