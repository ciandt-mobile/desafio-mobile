//
//  MovieAPI.swift
//  MoviesChallenge
//
//  Created by Leonardo Bortolotti on 14/12/19.
//  Copyright © 2019 Leonardo Bortolotti. All rights reserved.
//

import Foundation

class MovieAPI {
    
    class func requestPopularMovies(completionHandler: @escaping (_ data: Data?) -> ()) {
        let urlComponents = NSURLComponents(string: Constants.kAPI_URL + "/movie/popular")!
        urlComponents.queryItems = [
          URLQueryItem(name: "api_key", value: Constants.kAPI_KEY)
        ]
        if let url = urlComponents.url {
            URLSession.shared.dataTask(with: url) { data, response, error in
                completionHandler(data)
            }.resume()
        }
    }
    
}
