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

    func getPopularMovies(onSuccess: @escaping ((PopularMovies) -> Void), onFailure: @escaping (() -> Void)) {

        let url = ServicesConstants.POPULAR_MOVIES_URL

        let parameters: Parameters = [
            ServicesConstants.KEY : ""
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

    func getUpcomingMovies(onSuccess: @escaping ((UpcomingMovies) -> Void), onFailure: @escaping (() -> Void)) {

        let url = ServicesConstants.UPCOMING_MOVIES_URL

        let parameters: Parameters = [
            ServicesConstants.KEY : ""
        ]

        AF.request(url, method: .get, parameters: parameters, headers: ServicesConstants.MOVIES_DB_HEADER).validate().responseJSON { response in
            switch response.result {
            case .success:
                guard let data = response.data else {
                    print("No data was found in getUpcomingMovies API response")
                    onFailure()
                    return
                }

                do {
                    let upcomingMovies = try JSONDecoder().decode(UpcomingMovies.self, from: data)
                    onSuccess(upcomingMovies)
                }
                catch {
                    print("getUpcomingMovies decoder error: \(error)")
                    onFailure()
                }
            case let .failure(error):
                print("getUpcomingMovies API error: \(error)")
                onFailure()
            }
        }
    }

    func getImage(imagePath: String, onSuccess: @escaping ((UIImage) -> Void)) {

        let imageUrl = ServicesConstants.IMAGE_BASE_PATH + imagePath

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
