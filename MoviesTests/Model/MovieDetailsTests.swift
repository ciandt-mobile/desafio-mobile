//
//  MovieDetailsTests.swift
//  MoviesTests
//
//  Created by Piero Mattos on 02/08/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import XCTest
@testable import Movies

class MovieDetailsTests: XCTestCase {

    func testDecodingFromJSON() {
        guard
            let testJson = TestsHelper.loadSampleJsonData(named: "movie_details")
            else { return XCTFail("Could not load movie_details.json") }

        XCTAssertNoThrow( _ = try TestsHelper.defaultJSONDecoder.decode(MovieDetails.self, from: testJson))
    }
}
