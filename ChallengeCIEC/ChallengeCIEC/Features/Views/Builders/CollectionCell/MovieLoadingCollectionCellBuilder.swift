//
//  MovieLoadingCollectionCellBuilder.swift
//  ChallengeCIEC
//
//  Created by Guilherme Camilo Rosa on 26/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation
import UIKit

class MovieLoadingCollectionCellBuilder: CollectionCellBuilder {
    
    init(properties: CollectionCellBuilderProperties) {
        super.init(properties: properties, identifier: "MovieLoadingCollectionCell")
    }
    
    override func getCell() -> UICollectionViewCell {
        let cell = self.collection.dequeueReusableCell(withReuseIdentifier: self.identifier,
                                                       for: self.indexPath) as! MovieLoadingCollectionCell        
        return cell
    }
}
