//
//  ProductionCompanyTests.swift
//  MoviesTests
//
//  Created by Piero Mattos on 02/08/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import XCTest
@testable import Movies

class ProductionCompanyTests: XCTestCase {

    func testDecodingFromJSON() {
        guard
            let testJson = TestsHelper.loadSampleJsonData(named: "production_company")
            else { return XCTFail("Could not load production_company.json") }

        XCTAssertNoThrow( _ = try TestsHelper.defaultJSONDecoder.decode(ProductionCompany.self, from: testJson))
    }
}
