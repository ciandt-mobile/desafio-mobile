//
//  MovieAPI.swift
//  MoviesApp
//
//  Created by Luan Audibert on 11/18/19.
//  Copyright © 2019 Luan Audibert. All rights reserved.
//

import Foundation
import Moya

enum MovieAPI {
    case movies
    case genre
}

extension MovieAPI: TargetType, AccessTokenAuthorizable {
    var baseURL: URL {
        return URL(string: "https://api.themoviedb.org/3")!
    }
    
    var path: String {
        switch self {
        case .movies:
            return "/discover/movie"
        case .genre:
            return "/genre/movie/list"
        }
    }
    
    var method: Moya.Method {
        return .get
    }
    
    var sampleData: Data {
        return "{\"userToken\":\"123123\"}".utf8Encoded;
    }
    
    var task: Task {
        let params = ["api_key" : "2d746979abba3635175f20f1544ea72f"]
        return .requestParameters(parameters: params, encoding: URLEncoding(destination: .queryString))
    }
    
    var headers: [String : String]? {
        return nil
    }
    
    var authorizationType: AuthorizationType {
        return .none
    }
    
    
}

// MARK: - Helpers
private extension String {
    var urlEscaped: String {
        return addingPercentEncoding(withAllowedCharacters: .urlHostAllowed)!
    }
    
    var utf8Encoded: Data {
        return data(using: .utf8)!
    }
}
