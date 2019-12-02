//
//  CustomImageView.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 30/11/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import UIKit

let imageCache = NSCache<NSString, AnyObject>()

class CustomImageView: UIImageView {
    
    var imageURLString: String?

    func downloadFrom(stringURL: String) {
        self.image = nil
        self.imageURLString = stringURL
        
        if !stringURL.isEmpty, let strURL = stringURL.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed), let url = URL(string: Constants.apiURLImageBase + strURL) {
            // Checking from cache
            if let cachedImage = imageCache.object(forKey: strURL as NSString) as? UIImage, self.imageURLString == stringURL {
                DispatchQueue.main.async {
                    self.image = cachedImage
                    print("[LOG] Image URL: \(stringURL) returned from cache")
                }
                return
            }

            URLSession.shared.dataTask(with: url) { [weak self] data, response, error in
                guard
                    let httpURLResponse = response as? HTTPURLResponse, httpURLResponse.statusCode == 200,
                    let mimeType = response?.mimeType, mimeType.hasPrefix("image"),
                    let data = data, error == nil,
                    let image = UIImage(data: data)
                    else {
                        self?.showNoImage()
                        return
                }
                print("[LOG] Image URL: \(url)")
                DispatchQueue.main.async {
                    imageCache.setObject(image, forKey: strURL as NSString)
                    if stringURL == self?.imageURLString {
                        DispatchQueue.main.async {
                            self?.image = image
                        }
                        return
                    }
                }
            }.resume()
            
        } else {
            self.showNoImage()
            return
        }
    }

    func showNoImage() {
        DispatchQueue.main.async {
            self.image = UIImage(named: Constants.defaultImage)
        }
    }

}
