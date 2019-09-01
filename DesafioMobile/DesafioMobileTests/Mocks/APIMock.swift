//
//  APIMock.swift
//  DesafioMobileTests
//
//  Created by Eric Winston on 8/31/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import Foundation
import UIKit

@testable import DesafioMobile

class APIMock: APIClientInterface{
    
    var hasFetchedData: Bool = false
    var hasDowloadImage: Bool = false
    var getTheImageData: Bool = false
    
    func fetchData<T>(path: ApiPaths, type: T.Type, completion: @escaping (T, Error?) -> Void) where T : Decodable, T : Encodable {
        hasFetchedData = true
    }
    
    func downloadImage(path: String, completion: @escaping (UIImage?) -> Void) {
        hasDowloadImage = true
    }
    
    func getImageData(from url: URL, completion: @escaping (Data?, URLResponse?, Error?) -> ()) {
        hasDowloadImage = true
    }
    
}

