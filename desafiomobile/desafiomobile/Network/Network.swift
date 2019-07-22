//
//  Network.swift
//  desafiomobile
//
//  Created by Erasmo Oliveira on 21/07/19.
//  Copyright © 2019 Erasmo Oliveira. All rights reserved.
//

import UIKit

import Foundation
import Alamofire

class Network {
    
    //MARK: - Request
    class func request(network: NetworkRequest, completion: @escaping (_ result: RequestResponse?, _ error: Error?) -> Void) {
        print("\n\nRequest >>>>: \nMethod: \(network.method.rawValue),\nEndpoint: \(network.endpoint)\nParameters: \(network.parameters ?? [:])\nHeaders: \(network.header ?? [:])")
        
        let manager = Alamofire.SessionManager.default
        manager.session.configuration.timeoutIntervalForRequest = 60
        UIApplication.shared.isNetworkActivityIndicatorVisible = true

        manager.request(network.endpoint, method: network.method, parameters: network.parameters, encoding: network.enconding, headers: network.header).responseJSON { (response) in
            
            switch response.result {
            case let .success(result):
                let reqResponse = RequestResponse(response: result)
                
            case let .failure(error):
                completion(nil, error)
            }
        }
    }
}

import Foundation
import Alamofire

//MARK: - Protocol Request
protocol NetworkRequest {
    
    var baseURL: String {get}
    var endpoint: String {get}
    var parameters: Parameters? {get}
    var header: HTTPHeaders? {get}
    var enconding: ParameterEncoding {get}
    var method: HTTPMethod {get}
}

struct NetworkResponse<T>: Codable where T: Codable{
    
    var payload: T?
    var status: Bool?
    var messages: [String]?
}

struct RequestResponse {

    init(response: Any?) {
        
    }
}
