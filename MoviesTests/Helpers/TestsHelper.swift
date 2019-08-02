//
//  TestsHelper.swift
//  MoviesTests
//
//  Created by Piero Mattos on 02/08/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import XCTest

class TestsHelper {

    static var jsonDecoder: JSONDecoder {
        let decoder = JSONDecoder()
        decoder.keyDecodingStrategy = .convertFromSnakeCase
        return decoder
    }

    static func loadSampleJsonData(named name: String) -> Data? {
        guard
            let sampleJsonUrl = Bundle(for: TestsHelper.self).url(forResource: name, withExtension: "json")
            else { return nil }
        return try? Data(contentsOf: sampleJsonUrl)
    }

    static var defaultJSONDecoder: JSONDecoder {
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd"

        let decoder = JSONDecoder()
        decoder.keyDecodingStrategy = .convertFromSnakeCase
        decoder.dateDecodingStrategy = .formatted(formatter)
        return decoder
    }
}

// MARK: - Custom assertions

func XCTAssertNotNilAndEqual<T: Equatable>(_ value: T?, _ expectedValue: T) {
    guard
        let unwrappedValue = value
        else { return XCTFail("Value is equal to nil.") }

    if unwrappedValue != expectedValue {
        XCTFail("Value is not equal to expected value.")
    }
}
