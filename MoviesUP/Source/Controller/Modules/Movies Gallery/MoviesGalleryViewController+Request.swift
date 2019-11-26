//
//  MoviesGalleryViewController+Request.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 25/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import UIKit

extension MoviesGalleryViewController {
    
    //MARK: Parse Object
    func parseGalleryMoviesUpcoming() {
        ServiceManager().getMovies() { (upcoming, error) in
            guard let upcoming = upcoming else {
                return
            }
            self.galleryMovies = upcoming
        }
    }
    
    func parseGalleryMoviesPopular() {
        ServiceManager().getMoviesPopular { (popular, error) in
            guard let popular = popular else {
                return
            }
            self.galleryMovies = popular
        }
    }
}
