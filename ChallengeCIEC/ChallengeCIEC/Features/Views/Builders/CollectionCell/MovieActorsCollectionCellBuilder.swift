//
//  MovieActorsCollectionCellBuilder.swift
//  ChallengeCIEC
//
//  Created by Guilherme C Rosa on 30/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation
import UIKit

class MovieActorsCollectionCellBuilder: CollectionCellBuilder {
    
    private var cast: Cast?
    
    init(properties: CollectionCellBuilderProperties, cast: Cast?) {
        super.init(properties: properties, identifier: "MovieActorsCollectionCell")
        self.cast = cast
    }
    
    override func getCell() -> UICollectionViewCell {
        let cell = self.collection.dequeueReusableCell(withReuseIdentifier: self.identifier,
                                                       for: self.indexPath) as! MovieActorsCollectionCell
        cell.setup(cast: self.cast)
        
        return cell
    }
}
