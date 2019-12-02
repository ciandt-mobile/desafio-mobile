//
//  MovieRequest.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 30/11/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import Foundation

class MovieRequest {
    
    // MARK: - Singleton
    static private var shared: MovieRequest?
    private let urlString = Constants.apiURLBase

    // MARK: - INIT
    init() {}

    // MARK: - Functions
    static func sharedInstance() -> MovieRequest {
        if let object = self.shared {
            return object
        } else {
            self.shared = MovieRequest()
            return self.shared ?? MovieRequest()
        }
    }
    
    // MARK: - Functions
    func generateMovieRequest(endPoint: String, page: Int, query: String? = "") -> URLRequest? {

        if let encodedURL = urlString.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed), let fullURL = NSURLComponents(string: encodedURL + endPoint) {

            // Create array of existing query items
            var queryItems: [URLQueryItem] = fullURL.queryItems ??  []
            
            // Append the new query item in the existing query items array
            queryItems.append(URLQueryItem(name: "api_key", value: Constants.apiToken))
            queryItems.append(URLQueryItem(name: "page", value: "\(page)"))
            queryItems.append(URLQueryItem(name: "language", value: "en-US"))
            
            // Adding the query parameter when required
            if !(query ?? "").isEmpty {
                queryItems.append(URLQueryItem(name: "query", value: query))
            }

            // Append updated query items array in the url component object
            fullURL.queryItems = queryItems

            if let url = fullURL.url {
                var request = URLRequest(url: url)
                request.httpMethod = "GET"
                var headers = request.allHTTPHeaderFields ?? [:]
                headers["Content-Type"] = "application/json"
                request.allHTTPHeaderFields = headers
                print("[LOG] Generating request for \(request)")
                return request
            }
        }

        return nil
    }

}
