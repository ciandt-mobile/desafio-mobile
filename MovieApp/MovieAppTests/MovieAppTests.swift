//
//  MovieAppTests.swift
//  MovieAppTests
//
//  Created by Michele de Olivio Corrêa on 19/07/19.
//  Copyright © 2019 Michele de Olivio Corrêa. All rights reserved.
//

import XCTest
@testable import MovieApp

class MovieAppTests: XCTestCase {
    
    func test_givenNilResultsJson_thenInvalidResponse() {
        let sut = makeSUT(withFilename: "empty-nil-movies-response")
        XCTAssertNil(sut?.results)
    }
    
    func test_givenMissingResultsJson_thenInvalidResponse() {
        let sut = makeSUT(withFilename: "results-missing-movies-response")
        
        XCTAssertNil(sut?.results)
    }
    
    func test_givenWrongTypeResultsJson_thenInvalidResponse() {
        let sut = makeSUT(withFilename: "results-wrong-type-movies-response")
        XCTAssertNil(sut?.results)
    }
    
    func test_givenEmptyResultsJson_thenInvalidResponse() {
        let sut = makeSUT(withFilename: "results-empty-movies-response")
        XCTAssertNil(sut?.results.first)
    }
    
    func test_givenValidJson_thenValidResponse() {
        let sut = makeSUT(withFilename: "valid-movies-response")
        
        XCTAssertNotNil(sut?.results.first)
        XCTAssertTrue(sut?.results.count == 3)
    }
    
    // MARK: Helpers
    
    private func makeSUT(withFilename filename: String) -> MoviesResponse? {
        return CodableTestsHelper.decode(MoviesResponse.self, filename: filename)
    }

}
