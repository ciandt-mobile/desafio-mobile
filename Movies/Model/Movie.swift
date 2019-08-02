//
//  Movie.swift
//  Movies
//
//  Created by Piero Mattos on 26/07/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import Foundation
import HTTPeasy

struct Movie: Decodable {

    // MARK: - Properties

    let id: Int
    let title: String
    let overview: String
    let posterURL: URL
    let releaseDate: Date
    let releaseDateString: String

    // MARK: - Methods

    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)

        id = try container.decode(Int.self, forKey: .id)
        title = try container.decode(String.self, forKey: .title)
        overview = try container.decode(String.self, forKey: .overview)

        let posterPath = try container.decode(String.self, forKey: .posterPath)
        posterURL = URL(string: "https://image.tmdb.org/t/p/w780\(posterPath)")!

        releaseDate = try container.decode(Date.self, forKey: .releaseDate)
        let formatter = DateFormatter()
        formatter.dateStyle = .long
        releaseDateString = formatter.string(from: releaseDate)
    }

    // MARK: - Enums

    private enum CodingKeys: String, CodingKey {
        case id, title, overview, releaseDate, posterPath
    }

    // MARK: - Auxiliary structs

    private struct Results: Decodable {
        let results: [Movie]
        let totalResults: Int
    }
}

// MARK: - API Calls
extension Movie {

    static func fetchNowPlaying(_ completion: @escaping ([Movie]?, MoviesError?) -> Void) {
        fetchMovies("/movie/now_playing", completion: completion)
    }

    static func fetchUpcoming(_ completion: @escaping ([Movie]?, MoviesError?) -> Void) {
        fetchMovies("/movie/upcoming", completion: completion)
    }

    private static func fetchMovies(_ urlSufix: String, completion: @escaping ([Movie]?, MoviesError?) -> Void) {
        let urlString = Constants.baseURLString + urlSufix
        let params = ["api_key": Constants.apiKey, "language": "en-US"]
        let descriptor = Request.Descriptor(.GET, urlString, params)

        APIRequester.shared.request(descriptor) { data, error in
            guard
                let data = data,
                error == nil
                else { return completion(nil, .simple) }

            guard
                let results = try? JSONDecoder.defaultDecoder.decode(Movie.Results.self, from: data)
                else { return completion(nil, .simple) }

            completion(results.results, nil)
        }
    }
}
