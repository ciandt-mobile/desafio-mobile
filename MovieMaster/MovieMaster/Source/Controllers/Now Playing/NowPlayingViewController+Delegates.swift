//
//  NowPlayingViewController+Delegates.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 01/12/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import Foundation

extension NowPlayingViewController: GenericMoviesViewModelDelegate {

    internal func updateView() {
        self.nowPlayingCollectionView.reloadData()
    }

}
