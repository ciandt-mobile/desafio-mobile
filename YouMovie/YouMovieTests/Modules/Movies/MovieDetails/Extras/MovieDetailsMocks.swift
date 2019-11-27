//
//  MovieDetailsMocks.swift
//  YouMovieTests
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit
@testable import YouMovie

// MARK: - MovieDetailsWireframeMock

class MovieDetailsWireframeMock: MovieDetailsWireframeProtocol {

    // MARK: - Internal Properties

    var instantiateViewCalled: Bool = false
    var presentVideoCalled: Bool = false
    var presentDetailsCalled: Bool = false
    var closeViewCalled: Bool = false

    // MARK: - Internal Methods

    func instantiateView(with movie: MovieEntity) -> MovieDetailsView {
        self.instantiateViewCalled = true
        return MovieDetailsView()
    }

    func presentVideo(byKey videoKey: String) {
        self.presentVideoCalled = true
    }

    func presentDetails(from recommendation: MovieEntity) {
        self.presentDetailsCalled = true
    }

    func closeView() {
        self.closeViewCalled = true
    }
}

// MARK: - MovieDetailsViewMock

class MovieDetailsViewMock: MovieDetailsPresenterOutputProtocol {

    // MARK: - Internal Properties

    var updateUICalled: Bool = false

    // MARK: - Internal Methods

    func updateUI() {
        self.updateUICalled = true
    }
}

// MARK: - MovieDetailsPresenterMock

class MovieDetailsPresenterMock: MovieDetailsPresenterInputProtocol, MovieDetailsInteractorOutputProtocol {

    // MARK: - Internal Properties

    var viewDidLoadCalled: Bool = false
    var didTapPlayVideoCalled: Bool = false
    var didSelectRecommendationCalled: Bool = false
    var didTapCloseCalled: Bool = false
    var fetchMovieDetailsDidSucceedCalled: Bool = false
    var fetchMovieDetailsDidFailedCalled: Bool = false

    var movie: MovieEntity = MovieEntity()
    var hasVideo: Bool = false

    // MARK: - Internal Methods

    func viewDidLoad() {
        self.viewDidLoadCalled = true
    }

    func didTapPlayVideo() {
        self.didTapPlayVideoCalled = true
    }

    func didSelectRecommendation(_ recommendation: MovieEntity) {
        self.didSelectRecommendationCalled = true
    }

    func didTapClose() {
        self.didTapCloseCalled = true
    }

    func fetchMovieDetailsDidSucceed() {
        self.fetchMovieDetailsDidSucceedCalled = true
    }

    func fetchMovieDetailsDidFailed(_ error: RequestError) {
        self.fetchMovieDetailsDidFailedCalled = true
    }
}

// MARK: - MovieDetailsInteractorMock

class MovieDetailsInteractorMock: MovieDetailsInteractorInputProtocol {

    // MARK: - Internal Properties

    var fetchMovieDetailsCalled: Bool = false

    var movie: MovieEntity = MovieEntity()

    // MARK: - Internal Methods

    func fetchMovieDetails() {
        self.fetchMovieDetailsCalled = true
    }
}

// MARK: - MovieDetailsRequestsSuccessMock

class MovieDetailsRequestsSuccessMock: MovieDetailsRequestsProtocol {

    // MARK: - Internal Methods

    func fetchMovieDetails(byMovieID movieID: Int,
                           success: @escaping MovieDetailsSuccess,
                           failure: @escaping RequestFailure) {
        success(MovieEntity())
    }
}

// MARK: - MovieDetailsRequestsFailureMock

class MovieDetailsRequestsFailureMock: MovieDetailsRequestsProtocol {

    // MARK: - Internal Methods

    func fetchMovieDetails(byMovieID movieID: Int,
                           success: @escaping MovieDetailsSuccess,
                           failure: @escaping RequestFailure) {
        failure(.error(.internalError))
    }
}
