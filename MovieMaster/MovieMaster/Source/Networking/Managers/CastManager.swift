//
//  CastManager.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 30/11/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import Foundation

class CastManager {
    
    // MARK: - Singleton
    static private var shared: CastManager?
    private let jsonDecoder: JSONDecoder = {
        let jsonDecoder = JSONDecoder()
        jsonDecoder.keyDecodingStrategy = .convertFromSnakeCase
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-mm-dd"
        jsonDecoder.dateDecodingStrategy = .formatted(dateFormatter)
        return jsonDecoder
    }()
    
    // MARK: - INIT
    private init() {}
    
    // MARK: - Functions
    static func sharedInstance() -> CastManager {
        if let object = self.shared {
            return object
        } else {
            self.shared = CastManager()
            return self.shared ?? CastManager()
        }
    }
    
    private var cast: [Cast]?

    func fetchCast(endPoint: String, page: Int = 1, query: String? = "", movieId: Int, completion: @escaping (Result<DataCast, ErrorReturn>) -> ()) {

        if let request = MovieRequest.sharedInstance().generateMovieRequest(endPoint: translateMovieId(endPoint, movieId), page: page, query: query) {

            let dataTask = URLSession.shared.dataTask(with: request) { [weak self] (data, response, error) in
                guard let jsonData = data else {
                    completion(.failure(.noData))
                    return
                }

                do {
                    let castResult = try self?.jsonDecoder.decode(DataCast.self, from: jsonData)
                    if let castResult = castResult {
                        completion(.success(castResult))
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

    private func translateMovieId(_ endPoint: String, _ movieId: Int) -> String {
        return endPoint.replacingOccurrences(of: "movieid", with: "\(movieId)")
    }

}
