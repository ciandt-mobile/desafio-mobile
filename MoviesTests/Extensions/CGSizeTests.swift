//
//  CGSizeTests.swift
//  MoviesTests
//
//  Created by Piero Mattos on 02/08/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import XCTest
@testable import Movies

class CGSizeTests: XCTestCase {

    func testScaleToFit() {
        let container = CGSize(width: 100, height: 100)
        var size = CGSize(width: 200, height: 200)

        size.scaleToFit(inside: container)

        XCTAssertLessThan(size.width, container.width, "Width should be less than container width.")
        XCTAssertLessThan(size.height, container.height, "Height should be less than container height.")
    }
}
