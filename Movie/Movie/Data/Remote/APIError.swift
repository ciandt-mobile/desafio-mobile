//
//  APIError.swift
//  Movie
//
//  Created by Gabriel Guerrero on 25/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import Foundation

enum APIError: Error {
    case unauthorized //401
    case notFound //404
    case unreconized
}
