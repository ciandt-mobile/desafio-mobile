//
//  MovieDetailsEntitySpec.swift
//  YouMovieTests
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation
import ObjectMapper
import Quick
import Nimble
@testable import YouMovie

class MovieDetailsEntitySpec: QuickSpec {

    override func spec() {

        context("Parse Entity") {

            it("Should Parse All elements") {

                var movieDetailsJSON: JSON = [:]

                if let url = Bundle.main.url(forResource: "MovieDetailsJSON", withExtension: "json") {
                    do {
                        let data = try Data(contentsOf: url)
                        let json = try JSONSerialization.jsonObject(with: data, options: [])
                        movieDetailsJSON = json as? JSON ?? [:]
                    } catch let error {
                        print(error)
                    }
                }

                let movieEntity = MovieEntity(JSON: movieDetailsJSON)
                expect(movieEntity?.id).toNot(beNil())
                expect(movieEntity?.title).toNot(beNil())
                expect(movieEntity?.releaseDate).toNot(beNil())
                expect(movieEntity?.voteAverage).toNot(beNil())
                expect(movieEntity?.videos).toNot(beNil())
                expect(movieEntity?.overview).toNot(beNil())
                expect(movieEntity?.posterPathURL).toNot(beNil())
                expect(movieEntity?.backdropPathURL).toNot(beNil())
                expect(movieEntity?.runtime).toNot(beNil())
                expect(movieEntity?.budget).toNot(beNil())
                expect(movieEntity?.revenue).toNot(beNil())
                expect(movieEntity?.genres).toNot(beNil())
                expect(movieEntity?.cast).toNot(beNil())
                expect(movieEntity?.crew).toNot(beNil())
                expect(movieEntity?.recommendations).toNot(beNil())
            }
        }
    }
}
