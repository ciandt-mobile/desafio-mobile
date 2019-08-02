//
//  CrewMemberTests.swift
//  MoviesTests
//
//  Created by Piero Mattos on 02/08/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import XCTest
@testable import Movies

class CrewMemberTests: XCTestCase {

    func testDecodingFromJSON() {
        guard
            let testJson = TestsHelper.loadSampleJsonData(named: "crew_member")
            else { return XCTFail("Could not load crew_member.json") }

        XCTAssertNoThrow( _ = try TestsHelper.defaultJSONDecoder.decode(CrewMember.self, from: testJson))
    }

    func testPersonRepresentableConformance() {
        guard
            let testJson = TestsHelper.loadSampleJsonData(named: "crew_member"),
            let crewMember = try? TestsHelper.defaultJSONDecoder.decode(CrewMember.self, from: testJson)
            else { return XCTFail("Could not load crew_member.json") }

        XCTAssertEqual(crewMember.job, crewMember.role)
    }
}
