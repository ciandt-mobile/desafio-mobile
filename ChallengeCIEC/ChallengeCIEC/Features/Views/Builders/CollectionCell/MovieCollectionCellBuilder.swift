//
//  MovieCollectionCellBuilder.swift
//  ChallengeCIEC
//
//  Created by Guilherme Camilo Rosa on 26/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation
import UIKit

class MovieCollectionCellBuilder: CollectionCellBuilder {
 
    private var movies: [Movie]?
    
    init(properties: CollectionCellBuilderProperties, movies: [Movie]?) {
        super.init(properties: properties, identifier: "MovieCollectionCell")
        self.movies = movies
    }
    
    override func getCell() -> UICollectionViewCell {
        let cell = self.collection.dequeueReusableCell(withReuseIdentifier: self.identifier,
                                                      for: self.indexPath) as! MovieCollectionCell
        if let list = self.movies {
            cell.setup(movie: list[self.indexPath.item])
        }
        
        return cell
    }
}
