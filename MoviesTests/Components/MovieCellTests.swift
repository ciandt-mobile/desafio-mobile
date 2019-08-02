//
//  MovieCellTests.swift
//  MoviesTests
//
//  Created by Piero Mattos on 02/08/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import XCTest
@testable import Movies

class MovieCellTests: XCTestCase {

    func testLoadFromNib() {
        XCTAssertNoThrow( MovieCell.loadFromNib())
    }

    func testMovieDidSet() {
        guard
            let testJson = TestsHelper.loadSampleJsonData(named: "movie"),
            let movie = try? TestsHelper.defaultJSONDecoder.decode(Movie.self, from: testJson)
            else { return XCTFail("Could not load movie.json") }

        let movieCell = MovieCell.loadFromNib()

        movieCell.movie = movie

        XCTAssertEqual(movieCell.titleLabel.text, movie.title)
        XCTAssertEqual(movieCell.releaseDateLabel.text, movie.releaseDateString)
    }

    func testShadowSetup() {
        let movieCell = MovieCell.loadFromNib()

        XCTAssertEqual(movieCell.layer.shadowRadius, 20.0)
        XCTAssertEqual(movieCell.layer.shadowOpacity, 0.9)
        XCTAssertEqual(movieCell.layer.shadowOffset, CGSize.zero)
        XCTAssertFalse(movieCell.clipsToBounds)
    }
}
