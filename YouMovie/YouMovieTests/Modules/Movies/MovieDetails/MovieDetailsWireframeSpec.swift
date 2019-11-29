//
//  MovieDetailsWireframeSpec.swift
//  YouMovieTests
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit
import AVKit
import Quick
import Nimble
@testable import YouMovie

class MovieDetailsWireframeSpec: QuickSpec {

    override func spec() {

        var sut: MovieDetailsWireframe!

        beforeEach {
            sut = MovieDetailsWireframe()
        }

        afterEach {
            sut = nil
        }

        context("Verify Viper Properties") {

            it("Should View not be nil") {
                let navigationView = UINavigationController(rootViewController: UIViewController())
                UIApplication.shared.windows.first?.rootViewController = navigationView

                let view = sut.instantiateView(with: MovieEntity())
                navigationView.pushViewController(view, animated: false)
                expect(sut.view).toNot(beNil())
            }

            it("Should Presenter not be nil") {
                let navigationView = UINavigationController(rootViewController: UIViewController())
                UIApplication.shared.windows.first?.rootViewController = navigationView

                let view = sut.instantiateView(with: MovieEntity())
                navigationView.pushViewController(view, animated: false)

                let presenter = sut.view.presenter as? MovieDetailsPresenter
                expect(presenter).toNot(beNil())
            }

            it("Should Interactor not be nil") {
                let navigationView = UINavigationController(rootViewController: UIViewController())
                UIApplication.shared.windows.first?.rootViewController = navigationView

                let view = sut.instantiateView(with: MovieEntity())
                navigationView.pushViewController(view, animated: false)

                let presenter = sut.view.presenter as? MovieDetailsPresenter
                let interactor = presenter?.interactor as? MovieDetailsInteractor
                expect(interactor).toNot(beNil())
            }

            it("Should Interactor Output not be nil") {
                let navigationView = UINavigationController(rootViewController: UIViewController())
                UIApplication.shared.windows.first?.rootViewController = navigationView

                let view = sut.instantiateView(with: MovieEntity())
                navigationView.pushViewController(view, animated: false)

                let presenter = sut.view.presenter as? MovieDetailsPresenter
                let interactor = presenter?.interactor as? MovieDetailsInteractor
                expect(interactor?.output).toNot(beNil())
            }
        }

        context("Verify Navigation") {

            it("Should View push AVPlayerViewController") {
                let navigationView = UINavigationController(rootViewController: UIViewController())
                UIApplication.shared.windows.first?.rootViewController = navigationView

                let view = sut.instantiateView(with: MovieEntity())
                navigationView.pushViewController(view, animated: false)

                sut.presentVideo(byKey: "")
                expect(sut.view.presentedViewController?.isKind(of: AVPlayerViewController.self)).toEventually(beTrue())
            }

            it("Should View push MovieDetails") {
                let navigationView = UINavigationController(rootViewController: UIViewController())
                UIApplication.shared.windows.first?.rootViewController = navigationView

                let view = sut.instantiateView(with: MovieEntity())
                navigationView.pushViewController(view, animated: false)

                sut.presentDetails(from: MovieEntity())
                expect(navigationView.viewControllers.contains(where: { $0.isKind(of: MovieDetailsView.self) })).toEventually(beTrue())
            }

            it("Should MovieDetails be deallocated") {
                let navigationView = UINavigationController(rootViewController: UIViewController())
                UIApplication.shared.windows.first?.rootViewController = navigationView

                let view = sut.instantiateView(with: MovieEntity())
                navigationView.pushViewController(view, animated: false)

                sut.closeView()
                expect(navigationView.viewControllers.contains(where: { $0.isKind(of: MovieDetailsView.self) })).toEventually(beFalse())
            }
        }
    }
}
