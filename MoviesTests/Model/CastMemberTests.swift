//
//  CastMemberTests.swift
//  MoviesTests
//
//  Created by Piero Mattos on 02/08/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import XCTest
@testable import Movies

class CastMemberTests: XCTestCase {

    func testDecodingFromJSON() {
        guard
            let testJson = TestsHelper.loadSampleJsonData(named: "cast_member")
            else { return XCTFail("Could not load cast_member.json") }

        XCTAssertNoThrow( _ = try TestsHelper.defaultJSONDecoder.decode(CastMember.self, from: testJson))
    }

    func testPersonRepresentableConformance() {
        guard
            let testJson = TestsHelper.loadSampleJsonData(named: "cast_member"),
            let castMember = try? TestsHelper.defaultJSONDecoder.decode(CastMember.self, from: testJson)
            else { return XCTFail("Could not load cast_member.json") }

        XCTAssertEqual(castMember.characterName, castMember.role)
    }
}
