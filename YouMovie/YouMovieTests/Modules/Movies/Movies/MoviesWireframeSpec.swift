//
//  MoviesWireframeSpec.swift
//  YouMovieTests
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation
import Quick
import Nimble
@testable import YouMovie

class MoviesWireframeSpec: QuickSpec {

    override func spec() {

        var sut: MoviesWireframe!

        beforeEach {
            sut = MoviesWireframe()
        }

        afterEach {
            sut = nil
        }

        context("Verify Viper Properties") {

            it("Should View not be nil") {
                UIApplication.shared.windows.first?.rootViewController = sut.instantiateView()
                expect(sut.view).toNot(beNil())
            }

            it("Should Presenter not be nil") {
                UIApplication.shared.windows.first?.rootViewController = sut.instantiateView()
                let presenter = sut.view.presenter as? MoviesPresenter
                expect(presenter).toNot(beNil())
            }

            it("Should Interactor not be nil") {
                UIApplication.shared.windows.first?.rootViewController = sut.instantiateView()
                let presenter = sut.view.presenter as? MoviesPresenter
                let interactor = presenter?.interactor as? MoviesInteractor
                expect(interactor).toNot(beNil())
            }

            it("Should Interactor Output not be nil") {
                UIApplication.shared.windows.first?.rootViewController = sut.instantiateView()
                let presenter = sut.view.presenter as? MoviesPresenter
                let interactor = presenter?.interactor as? MoviesInteractor
                expect(interactor?.output).toNot(beNil())
            }
        }

        context("Verify Navigation") {

            it("Should View push MovieDetailsView") {
                let navigationView = UINavigationController(rootViewController: sut.instantiateView())
                UIApplication.shared.windows.first?.rootViewController = navigationView
                sut.presentDetails(from: MovieEntity())
                expect(navigationView.viewControllers.contains(where: { $0.isKind(of: MovieDetailsView.self) })).toEventually(beTrue())
            }
        }
    }
}
