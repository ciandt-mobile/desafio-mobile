//
//  MovieDetailsProtocols.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

// MARK: - Wireframe

protocol MovieDetailsWireframeProtocol: class {
    func instantiateView(with movie: MovieEntity) -> MovieDetailsView
    func presentVideo(byKey videoKey: String)
    func presentDetails(from recommendation: MovieEntity)
    func closeView()
}

// MARK: - View

protocol MovieDetailsPresenterOutputProtocol: class {
    func updateUI()
}

// MARK: - Presenter

protocol MovieDetailsPresenterInputProtocol: class {
    var movie: MovieEntity { get }
    var hasVideo: Bool { get }
    func viewDidLoad()
    func didTapPlayVideo()
    func didSelectRecommendation(_ recommendation: MovieEntity)
    func didTapClose()
}

protocol MovieDetailsInteractorOutputProtocol: class {
    func fetchMovieDetailsDidSucceed()
    func fetchMovieDetailsDidFailed(_ error: RequestError)
}

// MARK: - Interactor

protocol MovieDetailsInteractorInputProtocol: class {
    var movie: MovieEntity { get }
    func fetchMovieDetails()
}

