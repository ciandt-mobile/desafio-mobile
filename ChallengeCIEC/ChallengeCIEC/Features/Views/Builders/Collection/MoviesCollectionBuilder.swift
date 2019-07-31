//
//  MoviesCollectionBuilder.swift
//  ChallengeCIEC
//
//  Created by Guilherme Camilo Rosa on 25/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation
import UIKit

enum MoviesCollectionBuilderState {
    case Loading, Error, Empty, Success
}

protocol MoviesCollectionBuilderProtocol {
    func collectionDidSelectItem(movie: Movie)
}

class MoviesCollectionBuilder: NSObject {
    
    var collection: UICollectionView?
    var delegate: MoviesCollectionBuilderProtocol?
    
    private var state: MoviesCollectionBuilderState
    private var movies: [Movie]?
    private var error: String?
    
    init(state: MoviesCollectionBuilderState, movies: [Movie]?) {
        self.state = state
        self.movies = movies
    }
    
    init(state: MoviesCollectionBuilderState, error: String?) {
        self.state = state
        self.error = error
    }
}

extension MoviesCollectionBuilder: UICollectionViewDataSource, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumInteritemSpacingForSectionAt section: Int) -> CGFloat {
        return -15
    }
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return 0
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        if state == .Success, let mov = movies, mov.count > 0 {
            return mov.count
        } else {
            return 1
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        if state == .Success {
            let w = (collectionView.frame.width / 3)
            let h = w + (w * 0.4)
            return CGSize(width: w, height: h)
        } else {
            return CGSize(width: collectionView.frame.width, height: 100)
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        let properties = CollectionCellBuilderProperties(collection: collectionView, indexPath: indexPath)
        
        if state == .Success {
            return MovieCollectionCellBuilder(properties: properties, movies: self.movies).cell
        } else if state == .Loading {
            return MovieLoadingCollectionCellBuilder(properties: properties).cell
        } else {
            return MovieErrorCollectionCellBuilder(properties: properties, error: self.error).cell
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        if state == .Success, let list = movies {
            let movie = list[indexPath.item]
            self.delegate?.collectionDidSelectItem(movie: movie)
        }
    }
}
