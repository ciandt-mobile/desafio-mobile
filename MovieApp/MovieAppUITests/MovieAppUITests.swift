//
//  MovieAppUITests.swift
//  MovieAppUITests
//
//  Created by Michele de Olivio Corrêa on 19/07/19.
//  Copyright © 2019 Michele de Olivio Corrêa. All rights reserved.
//

import XCTest

class MovieAppUITests: XCTestCase {

    private let app = XCUIApplication()
    
    override func setUp() {
        continueAfterFailure = false

        app.launch()
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testClickOnPopularButton() {
        app.segmentedControls.buttons["Popular"].tap()
        
        let moviesSelectionText = app.staticTexts["Popular Movies"]
        XCTAssertTrue(moviesSelectionText.exists)
    }
    
    func testClickOnUpcomingButton() {
        app.segmentedControls.buttons["Popular"].tap()
        app.segmentedControls.buttons["Upcoming"].tap()
        
        let moviesSelectionText = app.staticTexts["Upcoming Movies"]
        XCTAssertTrue(moviesSelectionText.exists)
    }
    
    func testClickOnMovieNavigation() {
        app.collectionViews.children(matching:.any).element(boundBy: 0).tap()
        
        let movieDetailsViewController = app.otherElements["movie_details"]
        let movieDetailsViewControllerExists = movieDetailsViewController.waitForExistence(timeout: 5)
        XCTAssert(movieDetailsViewControllerExists)

        app.navigationBars.buttons["Home"].tap()

        let homeViewController = XCUIApplication().otherElements["home"]
        let homeViewControllerExists = homeViewController.waitForExistence(timeout: 5)
        XCTAssert(homeViewControllerExists)

    }
}
