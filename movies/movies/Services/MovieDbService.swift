//
//  MovieDbService.swift
//  movies
//
//  Created by Arthur Silva on 11/29/19.
//  Copyright © 2019 Arthur Silva. All rights reserved.
//

import Foundation
import Alamofire

class MovieDbService {

    func hasConnection() -> Bool {
        return NetworkReachabilityManager()?.isReachable ?? false
    }

    func getPopularMovies(page: Int, onSuccess: @escaping ((PopularMovies) -> Void), onFailure: @escaping (() -> Void)) {

        if !hasConnection() {
            onFailure()
            return
        }

        let url = ServicesConstants.POPULAR_MOVIES_URL

        let parameters: Parameters = [
            ServicesConstants.KEY : "e1431fa912601d9558f5c16a7c89fb5b",
            ServicesConstants.PAGE : page
        ]

        AF.request(url, method: .get, parameters: parameters, headers: ServicesConstants.MOVIES_DB_HEADER).validate().responseJSON { response in
            switch response.result {
            case .success:
                guard let data = response.data else {
                    print("No data was found in getPopularMovies API response")
                    onFailure()
                    return
                }

                do {
                    let popularMovies = try JSONDecoder().decode(PopularMovies.self, from: data)
                    onSuccess(popularMovies)
                }
                catch {
                    print("getPopularMovies decoder error: \(error)")
                    onFailure()
                }
            case let .failure(error):
                print("getPopularMovies API error: \(error)")
                onFailure()
            }
        }
    }

    func getMovieDetails(movieId: Int, onSuccess: @escaping ((MovieDetails) -> Void), onFailure: @escaping (() -> Void)) {

        if !hasConnection() {
            onFailure()
            return
        }

        let url = ServicesConstants.MOVIE_DETAILS_URL + String(movieId)

        let parameters: Parameters = [
            ServicesConstants.KEY : "e1431fa912601d9558f5c16a7c89fb5b"
        ]

        AF.request(url, method: .get, parameters: parameters, headers: ServicesConstants.MOVIES_DB_HEADER).validate().responseJSON { response in
            switch response.result {
            case .success:
                guard let data = response.data else {
                    print("No data was found in movieDetails API response")
                    onFailure()
                    return
                }

                do {
                    let movieDetails = try JSONDecoder().decode(MovieDetails.self, from: data)
                    onSuccess(movieDetails)
                }
                catch {
                    print("movieDetails decoder error: \(error)")
                    onFailure()
                }
            case let .failure(error):
                print("movieDetails API error: \(error)")
                onFailure()
            }
        }
    }

    func getMovieCast(movieId: Int, onSuccess: @escaping (([Actor]) -> Void), onFailure: @escaping (() -> Void)) {

        if !hasConnection() {
            onFailure()
            return
        }

        let url = ServicesConstants.MOVIE_DETAILS_URL + String(movieId) + ServicesConstants.MOVIE_CREDITS_PATH

        let parameters: Parameters = [
            ServicesConstants.KEY : "e1431fa912601d9558f5c16a7c89fb5b"
        ]

        AF.request(url, method: .get, parameters: parameters, headers: ServicesConstants.MOVIES_DB_HEADER).validate().responseJSON { response in
            switch response.result {
            case .success:
                guard let data = response.data else {
                    print("No data was found in MovieCredits API response")
                    onFailure()
                    return
                }

                do {
                    let movieCredits = try JSONDecoder().decode(MovieCredits.self, from: data)
                    onSuccess(movieCredits.cast)
                }
                catch {
                    print("MovieCredits decoder error: \(error)")
                    onFailure()
                }
            case let .failure(error):
                print("MovieCredits API error: \(error)")
                onFailure()
            }
        }
    }

    func downloadImage(imagePath: String, imageResolution: ImageResolution, onSuccess: @escaping ((UIImage) -> Void)) {

        if !hasConnection() {
            return
        }

        var imageUrl: String = ""

        switch imageResolution {
        case .low:
            imageUrl = ServicesConstants.IMAGE_LOW_RESOLUTION_BASE_PATH + imagePath
        case .medium:
            imageUrl = ServicesConstants.IMAGE_MEDIUM_RESOLUTION_BASE_PATH + imagePath
        case .high:
            imageUrl = ServicesConstants.IMAGE_HIGH_RESOLUTION_BASE_PATH + imagePath
        }

        AF.download(imageUrl).validate().responseData { response in
            switch response.result {
            case .success:
                if let data = response.value, let image = UIImage(data: data) {
                    onSuccess(image)
                }
                else {
                    print("Could not retrieve image data from \(imageUrl)")
                }
            case let .failure(error):
                print("Image API error: \(error)")
            }
        }
    }
}
