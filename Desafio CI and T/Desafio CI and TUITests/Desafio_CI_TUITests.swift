//
//  Desafio_CI_TUITests.swift
//  Desafio CI&TUITests
//
//  Created by Mateus Kamoei on 30/07/19.
//  Copyright © 2019 Mateus Kamoei. All rights reserved.
//

import XCTest

class Desafio_CI_TUITests: XCTestCase {

    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.

        // In UI tests it is usually best to stop immediately when a failure occurs.
        continueAfterFailure = false

        // UI tests must launch the application that they test. Doing this in setup will make sure it happens for each test method.
        XCUIApplication().launch()

        // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests before they run. The setUp method is a good place to do this.
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testPopularMoviesLoading() {
        expectation(for: NSPredicate(format: "count > 0"), evaluatedWith: XCUIApplication().collectionViews.cells, handler: nil)
        waitForExpectations(timeout: 3, handler: nil)
    }
    
    func testFilterUpcomingMovies() {
        let app = XCUIApplication()
        
        expectation(for: NSPredicate(format: "count > 0"), evaluatedWith: app.collectionViews.cells, handler: nil)
        waitForExpectations(timeout: 3, handler: nil)
        
        let firstCellsCount = app.collectionViews.cells.count
        app.switches.firstMatch.tap()
        let secondCellsCount = app.collectionViews.cells.count
        XCTAssertNotEqual(firstCellsCount, secondCellsCount)
    }

}
