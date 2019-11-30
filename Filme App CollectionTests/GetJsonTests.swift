//
//  Filme_App_CollectionTests.swift
//  Filme App CollectionTests
//
//  Created by Miguel Fernandes Lopes on 13/11/19.
//  Copyright © 2019 Miguel Fernandes Lopes. All rights reserved.
//

import XCTest
@testable import Filme_App_Collection

class FilmeAppCollectionTests: XCTestCase {
    
    // MARK: - Properties
    
    var sut: URLSession!
    
    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.
        super.setUp()

        sut = URLSession(configuration: .default)
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        sut = nil
        super.tearDown()
    }

    func testGetJson() {
      // given
      let url =
        URL(string: "https://api.themoviedb.org/3/discover/movie?api_key=2164a2fbfffba318c500028e8631ffd9")
      let promise = expectation(description: "Completion handler invoked")
      var statusCode: Int?
      var responseError: Error?

      // when
      let dataTask = sut.dataTask(with: url!) { data, response, error in
        statusCode = (response as? HTTPURLResponse)?.statusCode
        responseError = error
        promise.fulfill()
      }
      dataTask.resume()
      wait(for: [promise], timeout: 5)

      // then
      XCTAssertNil(responseError)
      XCTAssertEqual(statusCode, 200)
    }
    
    func testPerformanceExample() {
        // This is an example of a performance test case.
        measure {
            // Put the code you want to measure the time of here.
        }
    }

}
