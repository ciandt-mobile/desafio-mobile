//
//  MovieDetailsViewModel.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 01/12/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import Foundation

protocol MovieDetailsViewModelDelegate: class {
    func updateView()
}

class MovieDetailsViewModel {

    private var castCrew: DataCast?
    private var movieDetails: Movie?
    private var genres: [Genre]?
    private var genresDescriptions: String?
    weak var delegate: MovieDetailsViewModelDelegate?

    private func updateView() {
        self.delegate?.updateView()
    }

    func setCastCrew(cast: DataCast) {
        self.castCrew = cast
        updateView()
    }

    func setMovieDetails(movie: Movie) {
        self.movieDetails = movie
    }

    func getMovieDetails() -> Movie? {
        return movieDetails
    }

    func getAllCastCrew() -> DataCast? {
        return self.castCrew
    }

    func getCastCrew(by IndexPath: Int) -> Cast? {
        return self.castCrew?.cast[IndexPath]
    }

    func getCastCount() -> Int {
        return self.castCrew?.cast.count ?? 0
    }

    func setGenres(_ genres: [Genre]?) {
        self.genres = genres
    }

    func getGenres() -> [Genre]? {
        return genres
    }

    func mapGenres() -> String? {
        let genresFiltered = getGenres()?.filter{ genre in
            return movieDetails?.genreIds?.contains(where: {$0 == genre.id}) ?? false
        }

        if let genreNames = genresFiltered?.compactMap({$0.name}) {
            genresDescriptions = genreNames.joined(separator: ", ")
        }
        
        return genresDescriptions

    }
}
