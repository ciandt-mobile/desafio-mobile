//
//  MoviesCollectionManager.swift
//  MobileChallengeCIET
//
//  Created by Guilherme C Rosa on 22/07/19.
//  Copyright © 2019 Guilherme C Rosa. All rights reserved.
//

import Foundation
import UIKit

enum MoviesCollectionManagerState {
    case Loading, Error, Empty, Success
}

protocol MoviesCollectionManagerProtocol {
    func collectionDidSelectItem(movie: Movie)
}

class MoviesCollectionManager:NSObject {
    
    var delegate: MoviesCollectionManagerProtocol?
    
    private var state: MoviesCollectionManagerState
    private var movies: [Movie]?
    private var error: String?
    
    init(state: MoviesCollectionManagerState, movies: [Movie]?) {
        self.state = state
        self.movies = movies
    }
    
    init(state: MoviesCollectionManagerState, error: String?) {
        self.state = state
        self.error = error
    }
    
    func registerCells(collection: UICollectionView) {
        collection.register(UINib(nibName: "MovieCollectionCell", bundle: nil),
                                 forCellWithReuseIdentifier: "MovieCollectionCell")
        collection.register(UINib(nibName: "MovieLoadingCollectionCell", bundle: nil),
                                 forCellWithReuseIdentifier: "MovieLoadingCollectionCell")
        collection.register(UINib(nibName: "MovieErrorCollectionCell", bundle: nil),
                                 forCellWithReuseIdentifier: "MovieErrorCollectionCell")
    }
}

extension MoviesCollectionManager: UICollectionViewDataSource, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout {
    
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
            let h = w + (w * 0.5)
            return CGSize(width: w, height: h)
        } else {
            return CGSize(width: collectionView.frame.width, height: 100)
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        if state == .Success {
            return self.cellForItemSucess(collectionView, cellForItemAt: indexPath)
        } else if state == .Loading {
            return self.cellForItemLoading(collectionView, cellForItemAt: indexPath)
        } else {
            return self.cellForItemError(collectionView, cellForItemAt: indexPath)
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        if state == .Success, let list = movies {
            let movie = list[indexPath.item]
            self.delegate?.collectionDidSelectItem(movie: movie)
        }
    }
}


extension MoviesCollectionManager {
    
    private func cellForItemLoading(_ collectionView: UICollectionView,
                                    cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "MovieLoadingCollectionCell",
                                                      for: indexPath) as! MovieLoadingCollectionCell
        return cell
    }
    
    private func cellForItemError(_ collectionView: UICollectionView,
                                   cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "MovieErrorCollectionCell",
                                                      for: indexPath) as! MovieErrorCollectionCell
        if let err = error {
            cell.setup(error: err)
        }
        
        return cell
    }
    
    private func cellForItemSucess(_ collectionView: UICollectionView,
                                    cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "MovieCollectionCell",
                                                      for: indexPath) as! MovieCollectionCell
        if let list = self.movies {
            cell.setup(movie: list[indexPath.item])
        }
        
        return cell
    }
}
