//
//  MovieRequester.swift
//  desafiomobile
//
//  Created by Erasmo Oliveira on 21/07/19.
//  Copyright © 2019 Erasmo Oliveira. All rights reserved.
//

import Foundation
import Alamofire

enum MovieRequester : NetworkRequest {
    
    case popular([String: Any])
    case upcoming([String: Any])
    
    var baseURL: String { return "https://api.themoviedb.org/3/movie/" }
    var lang: String { return "en-US"}
    var apiKey: String { return "f3457e0e73837d80da2de63f310ea930"}

    var endpoint: String {
        switch self {
        case .popular(_): return baseURL + "popular/api_key=" + apiKey + "&language=" + lang + "&page=1"
        case .upcoming(_): return baseURL + "upcoming/api_key=" + apiKey + "&language=" + lang + "&page=1"
        }
    }
    
    var parameters: Parameters? {
        switch self {
        case let .popular(parameters): return params(parameters)
        case let .upcoming(parameters): return params(parameters)
        }
    }

    var header: HTTPHeaders? {
        let header: [String: String] = [:]
        return header
    }

    var enconding: ParameterEncoding { return JSONEncoding.default }

    var method: HTTPMethod {
        return .get
    }
    
    private func params(_ endpointParameters: [String: Any] = [:]) -> [String: Any] {
        
        let parameters: [String: Any]
        
        switch self {
        case .popular:
            parameters = ["": ""]
        case .upcoming:
            parameters = ["": ""]
        }
        
        return parameters
    }
    
}
