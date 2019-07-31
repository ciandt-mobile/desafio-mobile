//
//  MovieErrorCollectionCellBuilder.swift
//  ChallengeCIEC
//
//  Created by Guilherme Camilo Rosa on 26/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation
import UIKit

class MovieErrorCollectionCellBuilder: CollectionCellBuilder {
    
    private var error: String?
    
    init(properties: CollectionCellBuilderProperties, error: String?) {
        super.init(properties: properties, identifier: "MovieErrorCollectionCell")
        self.error = error
    }
    
    override func getCell() -> UICollectionViewCell {
        let cell = self.collection.dequeueReusableCell(withReuseIdentifier: self.identifier,
                                                      for: self.indexPath) as! MovieErrorCollectionCell
        if let err = self.error {
            cell.setup(error: err)
        }
        
        return cell
    }
}
