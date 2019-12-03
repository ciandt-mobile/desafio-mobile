//
//  MoviesUPTests.swift
//  MoviesUPTests
//
//  Created by Julio Garavelli on 27/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import XCTest
@testable import MoviesUP

class MoviesUPTests: XCTestCase {

    func testStringToDate() {
        let stringDate = "1991-03-08"
        
        let date = stringDate.getDate()
        let formatString = date?.getFormattedDate()
        
        XCTAssertEqual(formatString, "08/03/91")
        XCTAssertNotEqual(formatString, "08/03/1991")
    }
    
    func testGetYear() {
        let stringDate = "1991-03-08"
        
        guard let date = stringDate.getDate() else {
            return XCTFail("Date not founded")
        }

        XCTAssertNotNil(date.getYearString())
        XCTAssertEqual(date.getYearString(), "1991")
        XCTAssertNotEqual(date.getYearString(), "2019")
    }
}
