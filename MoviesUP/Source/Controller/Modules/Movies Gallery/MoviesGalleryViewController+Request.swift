//
//  MoviesGalleryViewController+Request.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 25/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import UIKit

extension MoviesGalleryViewController {
    
    //MARK: Parse Object Movies Upcoming/Popular
    func parseGalleryMoviesUpcoming() {
        self.galleryMovies = upcomingMovies

        guard upcomingMovies.isEmpty else {
            return
        }
    
        ServiceManager().getMovies() { (upcoming, error) in
            guard let upcoming: [GalleryMovies] = upcoming else {
                return
            }
            self.upcomingMovies = upcoming.sorted {
                $0.releaseDate?.getDateIso() ?? Date() > $1.releaseDate?.getDateIso() ?? Date() }
        }
    }

    func parseGalleryMoviesPopular() {
        self.galleryMovies = popularMovies

        guard popularMovies.isEmpty else {
            return
        }

        ServiceManager().getMoviesPopular { (popular, error) in
            guard let popular = popular else {
                return
            }
            self.popularMovies = popular
        }
    }
}
