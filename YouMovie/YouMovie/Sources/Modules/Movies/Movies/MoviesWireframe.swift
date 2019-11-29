//
//  MoviesWireframe.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

class MoviesWireframe: NSObject {

    // MARK: - Viper Properties

    private(set) weak var view: MoviesView!

    // MARK: - Private Properties

    private let nibName = String(describing: MoviesView.self)

    // MARK: - Private Methods

    private func setupView() -> MoviesView {

        let view = MoviesView(nibName: self.nibName, bundle: nil)
        let presenter = MoviesPresenter()
        let interactor = MoviesInteractor()

        presenter.wireframe = self
        presenter.view = view
        presenter.interactor = interactor

        view.presenter = presenter
        interactor.output = presenter

        self.view = view
        return view
    }
}

// MARK: - MoviesWireframeProtocol

extension MoviesWireframe: MoviesWireframeProtocol {

    func instantiateView() -> MoviesView {
        let view = self.setupView()
        return view
    }

    func presentDetails(from movie: MovieEntity) {
        guard let navigationView = self.view.navigationController else { return }
        let movieDetailsView = MovieDetailsWireframe().instantiateView(with: movie)
        navigationView.pushViewController(movieDetailsView, animated: true)
    }
}
