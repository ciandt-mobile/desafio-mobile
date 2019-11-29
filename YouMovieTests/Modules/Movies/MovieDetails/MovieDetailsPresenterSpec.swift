//
//  MovieDetailsPresenterSpec.swift
//  YouMovieTests
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation
import Quick
import Nimble
@testable import YouMovie

class MovieDetailsPresenterSpec: QuickSpec {

    override func spec() {

        var wireframe: MovieDetailsWireframeMock!
        var view: MovieDetailsViewMock!
        var sut: MovieDetailsPresenter!
        var interactor: MovieDetailsInteractorMock!

        beforeEach {
            wireframe = MovieDetailsWireframeMock()
            view = MovieDetailsViewMock()
            interactor = MovieDetailsInteractorMock()

            sut = MovieDetailsPresenter()
            sut.wireframe = wireframe
            sut.view = view
            sut.interactor = interactor
        }

        afterEach {
            wireframe = nil
            view = nil
            sut = nil
            interactor = nil
        }

        context("Verify Properties") {

            it("Should Viper Properties not be nil") {
                expect(sut.wireframe).toNot(beNil())
                expect(sut.view).toNot(beNil())
                expect(sut.interactor).toNot(beNil())
            }
        }

        context("Called Methods") {

            it("Should viewDidLoad call fetchMovieDetails") {
                sut.viewDidLoad()
                expect(interactor.fetchMovieDetailsCalled).to(beTrue())
            }

            it("Should didTapPlayVideo call presentVideo") {
                let movie = MovieEntity()
                let video = MovieVideoEntity()
                video.key = ""
                video.site = .youtube
                movie.videos = [video]
                interactor.movie = movie

                sut.didTapPlayVideo()
                expect(wireframe.presentVideoCalled).to(beTrue())
            }

            it("Should didTapPlayVideo not call presentVideo") {
                let movie = MovieEntity()
                let video = MovieVideoEntity()
                video.key = ""
                movie.videos = [video]
                interactor.movie = movie

                sut.didTapPlayVideo()
                expect(wireframe.presentVideoCalled).to(beFalse())
            }

            it("Should didSelectRecommendation call presentDetails") {
                sut.didSelectRecommendation(MovieEntity())
                expect(wireframe.presentDetailsCalled).to(beTrue())
            }

            it("Should didTapClose call closeView") {
                sut.didTapClose()
                expect(wireframe.closeViewCalled).to(beTrue())
            }

            it("Should fetchMovieDetailsDidSucceed call updateUI") {
                sut.fetchMovieDetailsDidSucceed()
                expect(view.updateUICalled).to(beTrue())
            }

            it("Should fetchMovieDetailsDidFailed call updateUI") {
                sut.fetchMovieDetailsDidSucceed()
                expect(view.updateUICalled).to(beTrue())
            }
        }
    }
}
