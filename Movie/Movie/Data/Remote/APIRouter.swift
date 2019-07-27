//
//  APIRouter.swift
//  Movie
//
//  Created by Gabriel Guerrero on 25/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import Foundation
import Alamofire

enum APIRouter: URLRequestConvertible {
    
    case upComing(upComingParams: UpComingParams)
    case popularMovie(popularMovieParams: PopularMovieParams)
    case movieDetails(movieId: String, movieParams: MovieDetailParams)
    
    // MARK: - HTTPMethod
    private var method: HTTPMethod {
        switch self {
        case .upComing:
            return .get
        case .popularMovie:
            return .get
        case .movieDetails:
            return .get
        }
    }
    
    //MARK: - Path
    private var path: String {
        switch self {
        case .upComing:
            return HTTP.PATHS.upComing
        case .popularMovie:
            return HTTP.PATHS.popular
        case .movieDetails(let movieId, _):
            return HTTP.PATHS.movieDetails + movieId
        }
    }
    
    //MARK: - HttpBody
    private var httpBody: [String: Any]? {
        switch self {
        case .upComing:
            return nil
        case .popularMovie:
            return nil
        case .movieDetails:
            return nil
        }
    }
    
    //MARK: - URLRequestConvertible
    func asURLRequest() throws -> URLRequest {
        let stringURL = HTTP.baseURL
        let url = try stringURL.asURL()
        
        var urlRequest = URLRequest(url: url.appendingPathComponent(path))
        
        //HTTP Method
        urlRequest.httpMethod = method.rawValue
        
        //Common Headers
        urlRequest.setValue(HTTPHeader.json.rawValue, forHTTPHeaderField: HTTPHeader.acceptType.rawValue)
        urlRequest.setValue(HTTPHeader.json.rawValue, forHTTPHeaderField: HTTPHeader.contentType.rawValue)
        
        //Parameters
        switch self {
        case .upComing(let parameters):
            urlRequest = try URLEncodedFormParameterEncoder.default.encode(parameters, into: urlRequest)
        case .popularMovie(let parameters):
            urlRequest = try URLEncodedFormParameterEncoder.default.encode(parameters, into: urlRequest)
        case .movieDetails(_, let parameters):
            urlRequest = try URLEncodedFormParameterEncoder.default.encode(parameters, into: urlRequest)
        }
        
        //HttpBody
        if let httpBody = httpBody {
            do {
                urlRequest.httpBody = try JSONSerialization.data(withJSONObject: httpBody, options: [])
            } catch {
                throw AFError.parameterEncodingFailed(reason: .jsonEncodingFailed(error: error))
            }
        }
        
        return urlRequest
    }
}
