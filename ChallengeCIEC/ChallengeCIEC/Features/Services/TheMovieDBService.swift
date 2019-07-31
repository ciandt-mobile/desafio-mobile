//
//  TheMovieDatabaseService.swift
//  ChallengeCIEC
//
//  Created by Guilherme Camilo Rosa on 25/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation
import UIKit
import Alamofire

class TheMovieDBService {
    
    typealias closureMovies = ([Movie]?, Error?) -> Void
    typealias closureMovieDetail = (MovieDetail?, Error?) -> Void
    typealias closureMovieCredits = (MovieCredits?, Error?) -> Void
    typealias closureMovieImage = (UIImage?, Error?) -> Void
    
    private var networking = TheMovieDBNetworking()
    
    func getMovies(category: MovieCategory, completion: @escaping closureMovies) {
        
        let endpoint = (category == .Upcoming) ? "movie/upcoming" : "movie/popular"
        
        self.networking.request(endpoint: endpoint) { (response, error) in
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
            
            self.networking.request(endpoint: endpoint) { (response, error) in
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
    
    func getMovieCredits(movie: MovieDetail, completion: @escaping closureMovieCredits) {
        
        if let id = movie.id {
            let endpoint = "movie/\(id)/credits"
            
            self.networking.request(endpoint: endpoint) { (response, error) in
                if let err = error {
                    completion (nil, err)
                } else {
                    guard let resp = response, let data = resp.data else { return }

                    do {
                        let result = try JSONDecoder().decode(MovieCredits.self, from: data)
                        completion(result, nil)
                    } catch {
                        completion(nil, error)
                    }
                }
            }
        }
    }
    
    func getMoviePoster(movie: Movie,  completion: @escaping closureMovieImage) {
        if let poster = movie.poster {
            self.networking.requestImage(image: poster) { (response, error) in
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
    
    func getMovieBackdrop(movie: MovieDetail,  completion: @escaping closureMovieImage) {
        if let poster = movie.backdrop {
            self.networking.requestImage(image: poster) { (response, error) in
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
    
    func getImage(imagePath: String, completion: @escaping closureMovieImage) {
        
        self.networking.requestImage(image: imagePath) { (response, error) in
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
