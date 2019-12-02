//
//  SearchViewController+Delegates.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 01/12/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import Foundation

extension SearchViewController: GenericMoviesViewModelDelegate {

    func updateView() {
        self.searchCollectionView.reloadData()
        updateInfoLabel()
    }

    func updateInfoLabel() {
        if self.isViewLoaded {
            infoLabel.text = "Total Results: \(viewModel.getMovieResult()?.totalResults ?? 0). Displaying: \(viewModel.getMoviesCount() ?? 0)"
        }
    }

}
