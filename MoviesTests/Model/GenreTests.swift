//
//  GenreTests.swift
//  MoviesTests
//
//  Created by Piero Mattos on 02/08/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import XCTest
@testable import Movies

class GenreTests: XCTestCase {

    func testDecodingFromJSON() {
        guard
            let testJson = TestsHelper.loadSampleJsonData(named: "genre")
            else { return XCTFail("Could not load genre.json") }

        XCTAssertNoThrow( _ = try TestsHelper.defaultJSONDecoder.decode(Genre.self, from: testJson))
    }
}
