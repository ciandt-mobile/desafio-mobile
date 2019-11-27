//
//  MovieDetailTests.swift
//  MoviesUPTests
//
//  Created by Julio Garavelli on 27/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import XCTest
@testable import MoviesUP

class MovieDetailTests: XCTestCase {
    
    var movieDetails: MovieDetail = MovieDetail()

    func testCategories() {
        movieDetails.genres = [Genres(id: 0, name: "Horror"),
                               Genres(id: 1, name: "Action"),
                               Genres(id: 2, name: "Drama")]
        
        XCTAssertEqual(movieDetails.categories, "Horror, Action, Drama")
        XCTAssertNotEqual(movieDetails.categories, "Horror, Action")
    }
    
    func testCategoriesEmpty() {
        movieDetails.genres = []
        
        XCTAssertEqual(movieDetails.categories, "")
    }
    
    func testRuntime() {
        movieDetails.runtimeValue = 180
        
        XCTAssertEqual(movieDetails.runtime, "180m")
        XCTAssertNotEqual(movieDetails.runtime, "180")
    }

    func testTimeAndCategories() {
        movieDetails.runtimeValue = 160
        
        movieDetails.genres = [Genres(id: 0, name: "Mystery"),
                               Genres(id: 1, name: "Thriller"),
                               Genres(id: 2, name: "Comedy")]
        
        XCTAssertEqual(movieDetails.timeAndCategories, "160m | Mystery, Thriller, Comedy")
        XCTAssertNotEqual(movieDetails.timeAndCategories, "16m | Mystery, Thriller, Comedy")
    }
}
