//
//  CollectionCellBuilder.swift
//  ChallengeCIEC
//
//  Created by Guilherme Camilo Rosa on 26/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation
import UIKit

class CollectionCellBuilder {
    
    private(set) var identifier: String
    private(set) var collection: UICollectionView
    private(set) var indexPath: IndexPath
    var cell: UICollectionViewCell { get { return self.getCell() }}
    
    init(properties: CollectionCellBuilderProperties, identifier: String) {
        self.collection = properties.collection
        self.indexPath = properties.indexPath
        self.identifier = identifier
        
        self.register()
    }
    
    private func register() {
        self.collection.register(UINib(nibName: self.identifier, bundle: nil),
                            forCellWithReuseIdentifier: self.identifier)
    }
    
    func getCell() -> UICollectionViewCell {
        let cell = self.collection.dequeueReusableCell(withReuseIdentifier: self.identifier,
                                                      for: self.indexPath)
        return cell
    }
}

struct CollectionCellBuilderProperties {
    
    var collection: UICollectionView
    var indexPath: IndexPath
}
