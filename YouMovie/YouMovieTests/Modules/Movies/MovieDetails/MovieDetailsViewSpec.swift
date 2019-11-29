//
//  MovieDetailsViewSpec.swift
//  YouMovieTests
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation
import Quick
import Nimble
@testable import YouMovie

class MovieDetailsViewSpec: QuickSpec {

    // swiftlint:disable function_body_length
    override func spec() {

        var sut: MovieDetailsView!
        var presenter: MovieDetailsPresenterMock!

        beforeEach {
            presenter = MovieDetailsPresenterMock()

            sut = MovieDetailsView(nibName: String(describing: MovieDetailsView.self), bundle: nil)
            sut.presenter = presenter

            UIApplication.shared.windows.first?.rootViewController = sut
        }

        afterEach {
            sut = nil
            presenter = nil
        }

        context("Verify Setup Properties") {

            it("Should Outlets not be nil") {
                sut.viewDidLoad()
                expect(sut.scrollView).toNot(beNil())
                expect(sut.stackView).toNot(beNil())

                expect(sut.backgropImageView).toNot(beNil())
                expect(sut.posterImageView).toNot(beNil())
                expect(sut.titleLabel).toNot(beNil())
                expect(sut.subtitleLabel).toNot(beNil())
                expect(sut.ratingView).toNot(beNil())
                expect(sut.playVideoButton).toNot(beNil())
                expect(sut.overviewLabel).toNot(beNil())

                expect(sut.durationLabel).toNot(beNil())
                expect(sut.budgetLabel).toNot(beNil())
                expect(sut.revenueLabel).toNot(beNil())
                expect(sut.genresLabel).toNot(beNil())
            }

            it("Should viewDidLoad setupUI") {

                let movie = MovieEntity()
                movie.title = "MovieDetailsTest"
                movie.overview = "MovieDetailsOverviewTest"
                movie.releaseDate = Date()

                presenter.movie = movie

                sut.viewDidLoad()
                expect(sut.statusBarStyle).to(equal(.lightContent))
                expect(sut.standaloneNavigationBarStyle).to(equal(.transparent))
                expect(sut.standaloneNavigationBar).toNot(beNil())

                expect(sut.titleLabel.text).to(equal("MovieDetailsTest"))
                expect(sut.subtitleLabel.text).to(equal(Date().formattedString))
                expect(sut.overviewLabel.text).to(equal("MovieDetailsOverviewTest"))
                expect(sut.playVideoButton.alpha).to(equal(0.0))

                presenter.movie.overview = ""
                sut.viewDidLoad()

                expect(sut.overviewLabel.text).to(equal("-"))
            }
        }

        context("Called Methods") {

            it("Should viewDidLoad be Called") {
                sut.viewDidLoad()
                expect(presenter.viewDidLoadCalled).to(beTrue())
            }

            it("Should didTapPlayVideoButton be Called") {
                sut.didTapPlayVideoButton()
                expect(presenter.didTapPlayVideoCalled).to(beTrue())
            }

            it("Should didTapBackButton be Called") {
                sut.didTapBackButton()
                expect(presenter.didTapCloseCalled).to(beTrue())
            }

            it("Should updateUI set behaviors") {
                presenter.hasVideo = true
                sut.updateUI()
                expect(sut.playVideoButton.alpha).to(equal(1.0))

                presenter.hasVideo = false
                sut.updateUI()
                expect(sut.playVideoButton.alpha).to(equal(0.0))
            }

            it("Should recommendationsView call didSelectRecommendation") {
                sut.recommendationsView(RecommendationsView(), didSelect: MovieEntity())
                expect(presenter.didSelectRecommendationCalled).to(beTrue())
            }

            it("Should setup all views") {

                if let url = Bundle.main.url(forResource: "MovieDetailsJSON", withExtension: "json") {
                    do {
                        let data = try Data(contentsOf: url)
                        let json = try JSONSerialization.jsonObject(with: data, options: [])
                        presenter.movie = MovieEntity(JSON: json as? JSON ?? [:]) ?? MovieEntity()
                    } catch let error {
                        print(error)
                    }
                }
                
                sut.viewDidLoad()
                sut.updateUI()
                expect(sut.stackView.arrangedSubviews.count).toEventually(equal(4))
            }
        }
    }
}
