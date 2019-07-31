//
//  Networking.swift
//  ChallengeCIEC
//
//  Created by Guilherme C Rosa on 27/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation
import UIKit
import Alamofire

class TheMovieDBNetworking {
    
    private let apiKey = "?api_key=404355d226ac1799dcb99c4468b4a545"
    private let apiURL = "https://api.themoviedb.org/3/"
    private let apiURLImage = "https://image.tmdb.org/t/p/w500/"
    
    typealias closureRequest = (DataResponse<Any>?, Error?) -> Void
    typealias closureRequestImage = (DataResponse<Data>?, Error?) -> Void
    
    func request(endpoint: String, completion: @escaping closureRequest) {
        
        let url = self.apiURL + endpoint + self.apiKey
        
        Alamofire.request(url).validate().responseJSON { response in
            if let error = response.error {
                completion(nil, error)
            } else {
                completion(response, nil)
            }
        }
    }
    
    func requestImage(image: String, completion: @escaping closureRequestImage) {
        
        let url = apiURLImage + image
        
        Alamofire.request(url).responseData { (response) in
            if let error = response.error {
                completion(nil, error)
            } else {
                completion(response, nil)
            }
        }
    }
}
