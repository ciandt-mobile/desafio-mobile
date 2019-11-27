//
//  MovieDetailsWireframe.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit
import XCDYouTubeKit
import AVKit

class MovieDetailsWireframe: NSObject {

    // MARK: - Viper Properties

    private(set) weak var view: MovieDetailsView!

    // MARK: - Private Properties

    private let nibName = String(describing: MovieDetailsView.self)

    // MARK: - Private Methods

    private func setupView(with movie: MovieEntity) -> MovieDetailsView {

        let view = MovieDetailsView(nibName: self.nibName, bundle: nil)
        let presenter = MovieDetailsPresenter()
        let interactor = MovieDetailsInteractor(movie: movie)

        presenter.wireframe = self
        presenter.view = view
        presenter.interactor = interactor

        view.presenter = presenter
        interactor.output = presenter

        self.view = view
        return view
    }
}

// MARK: - MovieDetailsWireframeProtocol

extension MovieDetailsWireframe: MovieDetailsWireframeProtocol {

    // MARK: - Internal Methods

    func instantiateView(with movie: MovieEntity) -> MovieDetailsView {
        let view = self.setupView(with: movie)
        return view
    }

    func presentVideo(byKey videoKey: String) {

        let playerView = AVPlayerViewController()

        self.view.present(playerView, animated: true) {

            XCDYouTubeClient.default().getVideoWithIdentifier(videoKey) { (video, _) in
                guard let streamURL = video?.streamURLs[XCDYouTubeVideoQuality.HD720.rawValue] else {
                    playerView.dismiss(animated: true)
                    return
                }
                playerView.player = AVPlayer(url: streamURL)
                playerView.player?.play()
            }
        }
    }

    func presentDetails(from recommendation: MovieEntity) {
        guard let navigationView = self.view.navigationController else { return }
        let movieDetails = MovieDetailsWireframe().instantiateView(with: recommendation)
        navigationView.pushViewController(movieDetails, animated: true)
    }

    func closeView() {
        self.view.navigationController?.popViewController(animated: true)
    }
}
