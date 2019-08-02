//
//  UIImageView.swift
//  Movies
//
//  Created by Piero Mattos on 29/07/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import UIKit

extension UIImageView {

    func loadImage(_ imageURL: URL, placeholder: UIImage? = nil) {
        DispatchQueue.main.async { [weak self] in self?.image = placeholder }

        if let cacheImage = Cache.getValue(forKey: imageURL.absoluteString) as? UIImage {
            DispatchQueue.main.async { [weak self] in self?.image = cacheImage }
            return
        }

        URLSession.shared.dataTask(with: imageURL) { [weak self] imageData, _, error in
            guard
                error == nil,
                let imageData = imageData,
                let loadedImage = UIImage(data: imageData)
                else { return }

            DispatchQueue.main.async {
                Cache.setValue(loadedImage, forKey: imageURL.absoluteString)
                self?.image = loadedImage
            }
        }.resume()
    }
}
