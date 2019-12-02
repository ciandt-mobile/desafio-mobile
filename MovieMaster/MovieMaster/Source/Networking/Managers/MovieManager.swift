//
//  MovieManager.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 30/11/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import Foundation

public enum ErrorReturn: Error {
    case apiError
    case invalidEndpoint
    case invalidResponse
    case noData
    case decodeError
}

class MovieManager {
    
    // MARK: - Singleton
    static private var shared: MovieManager?
    private let jsonDecoder: JSONDecoder = {
        let jsonDecoder = JSONDecoder()
        jsonDecoder.keyDecodingStrategy = .convertFromSnakeCase
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-mm-dd"
        jsonDecoder.dateDecodingStrategy = .formatted(dateFormatter)
        return jsonDecoder
    }()

    // MARK: - INIT
    init() {}

    // MARK: - Functions
    static func sharedInstance() -> MovieManager {
        if let object = self.shared {
            return object
        } else {
            self.shared = MovieManager()
            return self.shared ?? MovieManager()
        }
    }

    func fetchMovies(endPoint: String, page: Int = 1, query: String? = "", completion: @escaping (Result<MovieResult, ErrorReturn>) -> ()) {

        if let request = MovieRequest.sharedInstance().generateMovieRequest(endPoint: endPoint, page: page, query: query) {

            let dataTask = URLSession.shared.dataTask(with: request) { [weak self] (data, response, error) in
                guard let jsonData = data else {
                    completion(.failure(.noData))
                    return
                }

                do {
                    let movieResult = try self?.jsonDecoder.decode(MovieResult.self, from: jsonData)
                    if let movieResult = movieResult {
                        completion(.success(movieResult))
                    }
                } catch {
                    completion(.failure(.invalidResponse))
                }
            }

            dataTask.resume()

        } else {
            completion(.failure(.invalidEndpoint))
        }
        
    }

    func queryMovies(endPoint: String, page: Int = 1, query: String, completion: @escaping (Result<MovieResult, ErrorReturn>) -> ()) {

        fetchMovies(endPoint: endPoint, page: page, query: query) { (result) in
            switch result {
            case .failure(let error):
                print("[ERROR] Ops! \(String(describing: error))")
                completion(.failure(.noData))

            case .success(let data):
                completion(.success(data))
            }
        }

    }

}
