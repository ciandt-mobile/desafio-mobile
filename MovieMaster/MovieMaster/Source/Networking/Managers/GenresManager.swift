//
//  GenresManager.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 30/11/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import Foundation

class GenresManager {

    // MARK: - Singleton
    static private var shared: GenresManager?
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
    static func sharedInstance() -> GenresManager {
        if let object = self.shared {
            return object
        } else {
            self.shared = GenresManager()
            return self.shared ?? GenresManager()
        }
    }
    
    private var genres: [Genre]?

    func fetchGenres(endPoint: String, page: Int = 1, query: String? = "") {

        if let request = MovieRequest.sharedInstance().generateMovieRequest(endPoint: endPoint, page: page, query: query) {
            
            let dataTask = URLSession.shared.dataTask(with: request) { [weak self] (data, response, error) in
                guard let jsonData = data else {
                    print("[ERROR] Ops! \(String(describing: error))")
                    return
                }

                do {
                    let genresResult = try self?.jsonDecoder.decode(Genres.self, from: jsonData)
                    if let genres = genresResult?.genres {
                        DispatchQueue.main.async {
                            self?.genres = genres
                        }
                    }
                } catch {
                    print("[ERROR] Ops! \(String(describing: error))")
                }
            }
            
            dataTask.resume()
            
        } else {
            print("[ERROR] Unable to generate request for \(endPoint)")
        }
        
    }

    func getGenres() -> [Genre] {
        if let genres = genres {
            return genres
        } else {
            return [Genre]()
        }
    }

}
