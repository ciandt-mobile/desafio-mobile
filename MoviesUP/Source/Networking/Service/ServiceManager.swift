//
//  ServiceManager.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 24/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import Alamofire

class ServiceManager {
    
    private let baseURL = "https://api.themoviedb.org/3/movie/"
    private let apiKey = "0af4273a2dd990ab6b79a1f9c66bc614"
    
    private var params: [String: Any] {
        return ["api_key": apiKey]
    }
    
    func getMovies(completion: @escaping ([GalleryMovies]?, Error?) -> Void) {

        let fullUrl = "\(baseURL)upcoming"
        
        AF.request(fullUrl, parameters: params).responseData { (response) in
            guard let data = response.data else { return }
            
            do {
                let decoder = JSONDecoder()
                let dataMovies = try decoder.decode(DataGalleryMovies.self, from: data)
                //print(dataMovies)
                completion(dataMovies.results, nil)
            } catch let error {
                completion(nil, error)
                print("Error", error)
            }
        }
    }
    
    func getMoviesPopular(completion: @escaping ([GalleryMovies]?, Error?) -> Void) {

        let fullUrl = "\(baseURL)popular"
        
        AF.request(fullUrl, parameters: params).responseData { (response) in
            guard let data = response.data else { return }
            
            do {
                let decoder = JSONDecoder()
                let dataMovies = try decoder.decode(DataGalleryMovies.self, from: data)
                //print(dataMovies)
                completion(dataMovies.results, nil)
            } catch let error {
                completion(nil, error)
                print("Error", error)
            }
        }
    }
    
    func getMovieDetail(movieId: Int, completion: @escaping (MovieDetail?, Error?) -> Void) {

        let fullUrl = "\(baseURL)\(movieId)"

        AF.request(fullUrl, parameters: params).responseData { (response) in
            guard let data = response.data else { return }
            
            do {
                let decoder = JSONDecoder()
                let dataMovies = try decoder.decode(MovieDetail.self, from: data)
                completion(dataMovies, nil)
            } catch let error {
                completion(nil, error)
                print("Error", error)
            }
        }
    }

    func getCredits(movieId: Int, completion: @escaping ([Cast]?, Error?) -> Void) {

        let fullUrl = "\(baseURL)\(movieId)/credits"

        AF.request(fullUrl, parameters: params).responseData { (response) in
            guard let data = response.data else { return }
            
            do {
                let decoder = JSONDecoder()
                let dataMovies = try decoder.decode(DataCast.self, from: data)
                completion(dataMovies.cast, nil)
            } catch let error {
                completion(nil, error)
                print("Error", error)
            }
        }
    }
}
