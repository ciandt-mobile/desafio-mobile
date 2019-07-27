//
//  APIClient.swift
//  Movie
//
//  Created by Gabriel Guerrero on 25/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import Foundation
import Alamofire

class APIClient {
    
    @discardableResult
    private static func performRequest<T:Decodable>(route: APIRouter, decoder: JSONDecoder = JSONDecoder(), completion: @escaping (Result<T?, APIError>) -> Void) -> DataRequest {
        
        return AF.request(route).responseDecodable(completionHandler: { (response: DataResponse<T>) in
            let statusCode = response.response?.statusCode
            switch statusCode {
            case 200:
                do {
                    let responseResult = try response.result.get()
                    completion(.success(responseResult))
                } catch {
                    completion (.success(nil))
                }
            case 401:
                completion(.failure(.unauthorized))
            case 404:
                completion(.failure(.notFound))
            default:
                completion(.failure(.unreconized))
            }
        })
        
    }
    
    static func upComing(page: Int, completion: @escaping (Result<UpComingMovies?, APIError>) -> Void) {
        performRequest(route: APIRouter.upComing(upComingParams: UpComingParams(apiKey: HTTP.API_KEY,
                                                                                page: page)), completion: completion)
    }
    
    static func popular(page: Int, completion: @escaping (Result<PopularMovies?, APIError>) -> Void) {
        performRequest(route: APIRouter.popularMovie(popularMovieParams: PopularMovieParams(apiKey: HTTP.API_KEY,
                                                                                            page: page)), completion: completion)
    }
    
    static func getMovieDetails(movieId: String, completion: @escaping (Result<MovieDetail?, APIError>) -> Void) {
        performRequest(route: APIRouter.movieDetails(movieId: movieId,
                                                     movieParams: MovieDetailParams(apiKey: HTTP.API_KEY,
                                                                                    appendToResponse: HTTP.credits)), completion: completion)
    }
    
}
