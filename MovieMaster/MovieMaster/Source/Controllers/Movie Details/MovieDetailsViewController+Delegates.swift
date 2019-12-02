//
//  MovieDetailsViewController+Delegates.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 01/12/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import UIKit

extension MovieDetailsViewController: MovieDetailsViewModelDelegate {
    internal func updateView() {
        self.castCollectionView.reloadData()
    }
}
