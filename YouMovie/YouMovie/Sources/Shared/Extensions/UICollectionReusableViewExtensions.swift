//
//  UICollectionReusableViewExtensions.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

extension UICollectionReusableView {

    /// Method used to register reusable view to collectionView
    static func registerOn(_ collectionView: UICollectionView, forSupplementaryViewOfKind kind: String) {
        let reuseIdentifier = String(describing: self.self)
        collectionView.register(self.self, forSupplementaryViewOfKind: kind, withReuseIdentifier: reuseIdentifier)
    }
}
