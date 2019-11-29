//
//  LoadingCollectionReusableView.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

class LoadingCollectionReusableView: UICollectionReusableView {

    // MARK: - Outlets

    private var activityIndicator = UIActivityIndicatorView(style: .gray)

    // MARK: - Lifecycle

    override func prepareForReuse() {
        super.prepareForReuse()
        self.setupUI()
    }

    // MARK: - Private Properties

    private func setupUI() {
        self.activityIndicator.translatesAutoresizingMaskIntoConstraints = false
        self.addSubview(self.activityIndicator)
        self.activityIndicator.centerXAnchor.constraint(equalTo: self.centerXAnchor).isActive = true
        self.activityIndicator.centerYAnchor.constraint(equalTo: self.centerYAnchor).isActive = true
        self.activityIndicator.startAnimating()
    }
}
