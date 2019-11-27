//
//  UICollectionViewCellExtensions.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

extension UICollectionViewCell {

    /// Method used to register cell to collectionView
    ///
    /// - Parameter collectionView: Used to inform the collectionView that cell will be registered
    static func registerOn(_ collectionView: UICollectionView) {
        let reuseIdentifier = String(describing: self.self)
        let nib = UINib(nibName: reuseIdentifier, bundle: Bundle(for: self.self))
        collectionView.register(nib, forCellWithReuseIdentifier: reuseIdentifier)
    }
}
