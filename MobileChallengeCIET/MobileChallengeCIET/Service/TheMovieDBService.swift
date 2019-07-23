//
//  TheMovieDBService.swift
//  MobileChallengeCIET
//
//  Created by Guilherme C Rosa on 22/07/19.
//  Copyright © 2019 Guilherme C Rosa. All rights reserved.
//

import Foundation
import UIKit
import Alamofire

class TheMovieDBService {
    
    private let apiKey = "?api_key=404355d226ac1799dcb99c4468b4a545"
    private let apiURL = "https://api.themoviedb.org/3/"
    private let apiURLImage = "https://image.tmdb.org/t/p/w500/"
    
    typealias closureRequest = (DataResponse<Any>?, Error?) -> Void
    typealias closureRequestImage = (DataResponse<Data>?, Error?) -> Void
    
    private func request(url: String, completion: @escaping closureRequest) {
        
        AF.request(url).validate().responseJSON { response in
            if let error = response.error {
                completion(nil, error)
            } else {
                completion(response, nil)
            }
        }
    }
    
    private func requestImage(url: String, completion: @escaping closureRequestImage) {
        
        AF.request(url).responseData { (response) in
            if let error = response.error {
                completion(nil, error)
            } else {
                completion(response, nil)
            }
        }
    }
}

extension TheMovieDBService {
    
    typealias closureMovies = ([Movie]?, Error?) -> Void
    typealias closureMovieDetail = (MovieDetail?, Error?) -> Void
    typealias closureMovieImage = (UIImage?, Error?) -> Void
    
    func getMovies(category: MovieCategory, completion: @escaping closureMovies) {
        
        let endpoint = (category == .Upcoming) ? "movie/upcoming" : "movie/popular"
        let urlString = apiURL + endpoint + apiKey
        
        self.request(url: urlString) { (response, error) in
            if let err = error {
                completion (nil, err)
            } else {
                guard let resp = response, let data = resp.data else { return }
                
                do {
                    let result = try JSONDecoder().decode(MovieResult.self, from: data)
                    completion(result.movies, nil)
                } catch {
                    completion(nil, error)
                }
            }
        }
    }
    
    func getMovieDetail(movie: Movie, completion: @escaping closureMovieDetail) {
        
        if let id = movie.id {
            let endpoint = "movie/\(id)"
            let urlString = apiURL + endpoint + apiKey
            
            self.request(url: urlString) { (response, error) in
                if let err = error {
                    completion (nil, err)
                } else {
                    guard let resp = response, let data = resp.data else { return }
                    
                    do {
                        let result = try JSONDecoder().decode(MovieDetail.self, from: data)
                        completion(result, nil)
                    } catch {
                        completion(nil, error)
                    }
                }
            }
        }
    }
    
    func getMovieImage(movie: Movie, completion: @escaping closureMovieImage) {
        if let poster = movie.poster {
            let urlString = apiURLImage + poster
            
            self.requestImage(url: urlString) { (response, error) in
                if let err = error {
                    completion (nil, err)
                } else {
                    guard let resp = response, let data = resp.data else {
                        return completion (nil, nil)
                    }
                    
                    let image = UIImage(data: data)
                    completion(image, nil)
                }
            }
        }
    }
}

