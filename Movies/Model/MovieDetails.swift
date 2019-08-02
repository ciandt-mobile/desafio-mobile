//
//  MovieDetails.swift
//  Movies
//
//  Created by Piero Mattos on 28/07/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import Foundation
import HTTPeasy

struct MovieDetails: Decodable {

    // MARK: - Properties

    let budget: Int
    let genres: [Genre]
    let homepage: URL
    let id: Int
    let imdbId: String
    let overview: String
    let releaseDate: Date
    let runtime: Int
    let productionCompanies: [ProductionCompany]
    let videos: [Video]
    let cast: [CastMember]
    let crew: [CrewMember]

    // MARK: - Methods

    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)

        budget = try container.decode(Int.self, forKey: .budget)
        genres = try container.decode([Genre].self, forKey: .genres)

        let homepagePath = try container.decode(String.self, forKey: .homepage)
        homepage = URL(string: homepagePath)!

        id = try container.decode(Int.self, forKey: .id)
        imdbId = try container.decode(String.self, forKey: .imdbId)
        overview = try container.decode(String.self, forKey: .overview)
        releaseDate = try container.decode(Date.self, forKey: .releaseDate)
        runtime = try container.decode(Int.self, forKey: .runtime)
        productionCompanies = try container.decode([ProductionCompany].self, forKey: .productionCompanies)

        let videosContainer = try container.nestedContainer(keyedBy: VideoCodingKeys.self, forKey: .videos)
        videos = try videosContainer.decode([Video].self, forKey: .results)

        let creditsContainer = try container.nestedContainer(keyedBy: CreditsCodingKeys.self, forKey: .credits)
        cast = try creditsContainer.decode([CastMember].self, forKey: .cast)
        crew = try creditsContainer.decode([CrewMember].self, forKey: .crew)
    }

    // MARK: - Enums

    enum CodingKeys: String, CodingKey {
        case budget, genres, homepage, id, imdbId, overview, releaseDate, runtime, productionCompanies, videos, credits
    }

    enum VideoCodingKeys: String, CodingKey {
        case results
    }

    enum CreditsCodingKeys: String, CodingKey {
        case cast, crew
    }
}

// MARK: - API Calls

extension MovieDetails {

    static func load(forMovie movie: Movie, completionHandler: @escaping (MovieDetails?, MoviesError?) -> Void) {
        let urlString = Constants.baseURLString + "/movie/\(movie.id)"
        let params = ["api_key": Constants.apiKey, "language": "en-US", "append_to_response": "videos,credits"]
        let descriptor = Request.Descriptor(.GET, urlString, params)

        APIRequester.shared.request(descriptor) { data, error in
            guard
                let data = data,
                error == nil
                else { return completionHandler(nil, .simple) }

            guard
                let movieDetails = try? JSONDecoder.defaultDecoder.decode(MovieDetails.self, from: data)
                else {
                    return completionHandler(nil, .simple)
            }

            completionHandler(movieDetails, nil)
        }
    }
}
