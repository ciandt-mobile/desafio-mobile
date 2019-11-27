//
//  UIImageViewExtensions.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit
import Kingfisher

// MARK: - Download Image

extension UIImageView {

    /// Method used to download image directly to ImageView
    ///
    /// - Parameters:
    ///   - urlString: URL String of image to be dowloaded
    ///   - completion: Completion block that is called when image download completes
    func downloadImage(fromURLString urlString: String, completion: (() -> Void)? = nil) {

        self.kf.setImage(with: URL(string: urlString),
                         options: [.transition(.fade(0.3))]) { _ in
            completion?()
        }
    }

    /// Cancels the image download task of the image view if it is running.
    func cancelDownloadImage() {
        self.kf.cancelDownloadTask()
    }
}
