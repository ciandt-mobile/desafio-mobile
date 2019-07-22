//
//  CodableTestHelper.swift
//  MovieAppTests
//
//  Created by Michele de Olivio Corrêa on 20/07/19.
//  Copyright © 2019 Michele de Olivio Corrêa. All rights reserved.
//

import Foundation

class CodableTestsHelper {
    
    static func decode<T: Decodable>(_ value: T.Type, withData data: Data?) -> T? {
        
        guard let data = data else { return nil }
        
        do {
            return try JSONDecoder().decode(T.self, from: data)
        } catch {
            print("Decode error: \(error)")
            return nil
        }
    }
    
    static func decode<T: Decodable>(_ decodable: T.Type, filename: String) -> T? {
        guard let path = Bundle(for: type(of: CodableTestsHelper().self)).path(forResource: filename, ofType: "json"),
            let data = try? Data(contentsOf: URL(fileURLWithPath: path), options: .mappedIfSafe) else {
                return nil
        }
        return CodableTestsHelper.decode(decodable.self, withData: data)
    }
}
