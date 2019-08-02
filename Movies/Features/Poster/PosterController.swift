//
//  PosterController.swift
//  Movies
//
//  Created by Piero Mattos on 28/07/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import UIKit

class PosterController: UIViewController, UIScrollViewDelegate {

    // MARK: - Properties

    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet weak var posterImageView: UIImageView!

    // MARK: - Lifecycle

    override func viewDidLoad() {
        super.viewDidLoad()
        scrollView.zoomScale = 1.0
        scrollView.minimumZoomScale = 1.0
        scrollView.maximumZoomScale = 10.0
    }

    // MARK: - Methods

    @IBAction func didTapDone(_ sender: Any) {
        navigationController?.dismiss(animated: true, completion: nil)
    }

    // MARK: - UIScrollViewDelegate

    func viewForZooming(in scrollView: UIScrollView) -> UIView? {
        return posterImageView
    }
}
