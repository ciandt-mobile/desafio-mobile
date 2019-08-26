//
//  Error.swift
//  Movs
//
//  Created by Eduardo Pereira on 28/07/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit

enum NetworkError: Error {
    case invalidURL(String)
    case invalidVideo(String)
    case movieNotFound(String)
}
