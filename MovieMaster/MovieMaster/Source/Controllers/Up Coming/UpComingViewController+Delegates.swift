//
//  UpComingViewController+Delegates.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 01/12/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import Foundation

extension UpComingViewController: GenericMoviesViewModelDelegate {

    internal func updateView() {
        self.upComingCollectionView.reloadData()
    }

}
