//
//  MovieDetailViewController+Request.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 27/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import UIKit

extension MovieDetailViewController {
    
    //MARK: Parse Object Movies Detail/Credits
    func getMovieDetail() {
        guard let movieID = movieID else { return }

        ServiceManager().getMovieDetail(movieId: movieID) { (detail, error) in
            guard let detail = detail else {
                return
            }
            self.movieDetail = detail
        }
    }
    
    func getCredits() {
        guard let movieID = movieID else { return }

        ServiceManager().getCredits(movieId: movieID) { (cast, error) in
            guard let cast = cast else {
                return
            }
            self.cast = cast
        }
    }
}
