//
//  ServiceManager.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 24/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import Alamofire

class ServiceManager {
    
    func getMovies(completion: @escaping ([GalleryMovies]?, Error?) -> Void) {

        let fullUrl = "https://api.themoviedb.org/3/movie/upcoming?api_key=0af4273a2dd990ab6b79a1f9c66bc614"
        
        AF.request(fullUrl).responseData { (response) in
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
}
