//
//  LoadingViewTests.swift
//  MoviesTests
//
//  Created by Piero Mattos on 02/08/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import XCTest
@testable import Movies

class LoadingViewTests: XCTestCase {

    func testLoadFromNib() {
        XCTAssertNoThrow( LoadingView.loadFromNib())
    }
}
