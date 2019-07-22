//
//  MovieStore.swift
//  MovieApp
//
//  Created by Michele de Olivio Corrêa on 19/07/19.
//  Copyright © 2019 Michele de Olivio Corrêa. All rights reserved.
//

import Foundation

public enum MovieError: Error {
    case invalidURL
    case invalidResponse
    case noData
    case serializationError
    case unknown
}

public enum MoviesSelectionType: String {
    case popular
    case upcoming
    
    public var labelText: String {
        switch self {
        case .upcoming: return "Upcoming Movies"
        case .popular: return "Popular Movies"
        }
    }
    
    public init?(index: Int) {
        switch index {
        case 0: self = .upcoming
        case 1: self = .popular
        default: return nil
        }
    }
}

class MovieStore {
    public static let shared = MovieStore()
    private init() {}
    
    private let apiKey = "8592b59b36f654ab843fe9b988c21405"
    private let baseUrl = "https://api.themoviedb.org/3"
    
    public func fetchMovies(for type: MoviesSelectionType, page: Int? = nil, successHandler: @escaping (_ response: MoviesResponse) -> Void, errorHandler: @escaping(_ error: Error) -> Void) {
        
        var stringUrl = "\(baseUrl)/movie/\(type)?api_key=\(apiKey)"
        
        if let page = page {
            stringUrl.append("&page=\(page)")
        }
        
        guard let url = URL(string: stringUrl) else {
            errorHandler(MovieError.invalidURL)
            return
        }
        
        URLSession.shared.dataTask(with: url) { (data, response, error) in
            if error != nil {
                errorHandler(MovieError.unknown)
                return
            }
            
            guard let httpResponse = response as? HTTPURLResponse, 200..<300 ~= httpResponse.statusCode else {
                errorHandler(MovieError.invalidResponse)
                
                return
            }
            
            guard let data = data else {
                errorHandler(MovieError.noData)
                return
            }
            
            do {
                let response = try JSONDecoder().decode(MoviesResponse.self, from: data)
                DispatchQueue.main.async {
                    successHandler(response)
                }
            } catch {
                errorHandler(MovieError.serializationError)
            }
            }.resume()
    }
    
    
    
    public func fetchMovie(with id: Int, successHandler: @escaping (_ response: Movie) -> Void, errorHandler: @escaping(_ error: Error) -> Void) {
        
        guard let url = URL(string: "\(baseUrl)/movie/\(id)?api_key=\(apiKey)&append_to_response=credits") else {
            errorHandler(MovieError.serializationError)
            return
        }
        
        URLSession.shared.dataTask(with: url) { (data, response, error) in
            if error != nil {
                errorHandler(MovieError.serializationError)
                return
            }
            
            guard let httpResponse = response as? HTTPURLResponse, 200..<300 ~= httpResponse.statusCode else {
                errorHandler(MovieError.serializationError)

                return
            }
            
            guard let data = data else {
                errorHandler(MovieError.serializationError)
                return
            }
            
            do {
                let response = try JSONDecoder().decode(Movie.self, from: data)
                DispatchQueue.main.async {
                    successHandler(response)
                }
            } catch {
                errorHandler(MovieError.serializationError)
            }
        }.resume()
        
    }
}
