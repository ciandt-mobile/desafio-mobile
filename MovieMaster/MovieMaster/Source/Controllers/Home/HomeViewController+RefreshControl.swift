//
//  HomeViewController+RefreshControl.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 01/12/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import Foundation

extension HomeViewController {
    
    // doing a pull to refresh
    func setupRefreshControl() {
        if #available(iOS 10.0, *) {
            scrollView.refreshControl = refreshControl
        } else {
            scrollView.addSubview(refreshControl)
        }
        self.refreshControl.attributedTitle = NSAttributedString(string: "Refreshing")
        self.refreshControl.addTarget(self, action: #selector(refreshTableView(_:)), for: .valueChanged)
    }
    
    @objc private func refreshTableView(_ sender: Any) {
        // getting a random page for the pull to refresh to bring different movies
        if let nowPlayingTotalPages = viewModel.nowPlayingViewModel.getTotalPages() {
            fetchMoviesGeneric(endPoint: Constants.nowPlayingEndPoint, page: Int.random(in: 1 ... nowPlayingTotalPages))
        }
        
        if let popularTotalPages = viewModel.popularViewModel.getTotalPages() {
            fetchMoviesGeneric(endPoint: Constants.popularEndPoint, page: Int.random(in: 1 ... popularTotalPages))
        }
        
        if let upComingTotalPages = viewModel.upComingViewModel.getTotalPages() {
            fetchMoviesGeneric(endPoint: Constants.upComingEndPoint, page: Int.random(in: 1 ... upComingTotalPages))
        }
        
    }
    
    @objc func finishedRefreshing() {
        refreshControl.endRefreshing()
    }
    
}
