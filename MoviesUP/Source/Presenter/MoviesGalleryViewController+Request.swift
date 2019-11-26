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
    func parseGalleryMovies() {
        ServiceManager().getMovies() { (movies, error) in
            guard let movies = movies else {
                return
            }
            self.galleryMovies = movies
        }
    }
}
