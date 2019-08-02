//
//  JSONDecoder.swift
//  Movies
//
//  Created by Piero Mattos on 02/08/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import Foundation

extension JSONDecoder {

    static var defaultDecoder: JSONDecoder {
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd"

        let decoder = JSONDecoder()
        decoder.keyDecodingStrategy = .convertFromSnakeCase
        decoder.dateDecodingStrategy = .formatted(formatter)
        return decoder
    }
}
