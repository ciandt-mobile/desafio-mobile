//
//  HomeViewController+Delegates.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 01/12/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import Foundation

extension HomeViewController: NowPlayingDelegate, PopularDelegate, UpComingDelegate {
    func didSelectItemAt(with movie: Movie) {
        self.movieDetails = movie
        performSegue(withIdentifier: MovieDetailsViewController.segueIdentifier, sender: self)
    }
}
