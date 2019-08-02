//
//  VideoTests.swift
//  MoviesTests
//
//  Created by Piero Mattos on 02/08/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import XCTest
@testable import Movies

class VideoTests: XCTestCase {

    func testDecodingFromJSON() {
        guard
            let testJson = TestsHelper.loadSampleJsonData(named: "video_youtube_trailer")
            else { return XCTFail("Could not load video_youtube_trailer.json") }

        XCTAssertNoThrow( _ = try TestsHelper.defaultJSONDecoder.decode(Video.self, from: testJson))
    }

    func testIsYouTube() {
        guard
            let youtubeJson = TestsHelper.loadSampleJsonData(named: "video_youtube_trailer"),
            let youtubeVideo = try? TestsHelper.defaultJSONDecoder.decode(Video.self, from: youtubeJson)
            else { return XCTFail("Could not load video_youtube_trailer.json") }

        XCTAssertTrue(youtubeVideo.isYouTube)

        guard
            let vimeoJson = TestsHelper.loadSampleJsonData(named: "video_vimeo"),
            let vimeoVideo = try? TestsHelper.defaultJSONDecoder.decode(Video.self, from: vimeoJson)
            else { return XCTFail("Could not load video_vimeo.json") }

        XCTAssertFalse(vimeoVideo.isYouTube)
    }

    func testIsTrailer() {
        guard
            let youtubeJson = TestsHelper.loadSampleJsonData(named: "video_youtube_trailer"),
            let youtubeVideo = try? TestsHelper.defaultJSONDecoder.decode(Video.self, from: youtubeJson)
            else { return XCTFail("Could not load video_youtube_trailer.json") }

        XCTAssertTrue(youtubeVideo.isTrailer)

        guard
            let teaserJson = TestsHelper.loadSampleJsonData(named: "video_youtube_teaser"),
            let teaserVideo = try? TestsHelper.defaultJSONDecoder.decode(Video.self, from: teaserJson)
            else { return XCTFail("Could not video_youtube_teaser.json") }

        XCTAssertFalse(teaserVideo.isTrailer)
    }
}
