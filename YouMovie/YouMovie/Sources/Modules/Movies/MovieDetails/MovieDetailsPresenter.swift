//
//  MovieDetailsPresenter.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation

class MovieDetailsPresenter: NSObject {

    // MARK: - Viper Properties

    var wireframe: MovieDetailsWireframeProtocol!
    weak var view: MovieDetailsPresenterOutputProtocol!
    var interactor: MovieDetailsInteractorInputProtocol!

    // MARK: - Internal Properties

    var movie: MovieEntity {
        return self.interactor.movie
    }

    var hasVideo: Bool {
        guard let videos = self.movie.videos else { return false }
        return videos.contains(where: { $0.site == .youtube })
    }
}

// MARK: - MovieDetailsPresenterInputProtocol

extension MovieDetailsPresenter: MovieDetailsPresenterInputProtocol {

    // MARK: - Internal Methods

    func viewDidLoad() {
        self.interactor.fetchMovieDetails()
    }

    func didTapPlayVideo() {
        guard let videos = self.movie.videos,
            let video = videos.first(where: { $0.site == .youtube }),
            let videoKey = video.key else { return }
        self.wireframe.presentVideo(byKey: videoKey)
    }

    func didSelectRecommendation(_ recommendation: MovieEntity) {
        self.wireframe.presentDetails(from: recommendation)
    }

    func didTapClose() {
        self.wireframe.closeView()
    }
}

// MARK: - MovieDetailsInteractorOutputProtocol

extension MovieDetailsPresenter: MovieDetailsInteractorOutputProtocol {

    // MARK: - Internal Methods

    func fetchMovieDetailsDidSucceed() {
        self.view.updateUI()
    }

    func fetchMovieDetailsDidFailed(_ error: RequestError) {
        self.view.updateUI()
    }
}
