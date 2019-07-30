//
//  ErrorResponseModel.swift
//  TheMovieApp
//
//  Created by Wilson Kim on 25/06/19.
//  Copyright © 2019 WilsonKim. All rights reserved.
//

import Foundation

class ErrorResponseModel: Codable {
    
    var statusCode:Int
    var errorMessage:String
    
    enum CodingKeys: String, CodingKey {
        case statusCode = "status_code"
        case errorMessage = "status_message"
    }
    
    func getError() -> Error {
        return NSError(domain: Bundle.main.bundleIdentifier ?? "", code:statusCode, userInfo:[ NSLocalizedDescriptionKey: errorMessage])
    }
}
