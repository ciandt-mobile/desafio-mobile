//
//  MovieTests.swift
//  MoviesTests
//
//  Created by Piero Mattos on 02/08/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import XCTest
@testable import Movies

class MovieTests: XCTestCase {

    func testDecodingFromJSON() {
        guard
            let testJson = TestsHelper.loadSampleJsonData(named: "movie")
            else { return XCTFail("Could not load movie.json") }

        XCTAssertNoThrow( _ = try TestsHelper.defaultJSONDecoder.decode(Movie.self, from: testJson))
    }
}
