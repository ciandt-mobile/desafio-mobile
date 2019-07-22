//
//  MovieCollectionViewCell.swift
//  MovieApp
//
//  Created by Michele de Olivio Corrêa on 19/07/19.
//  Copyright © 2019 Michele de Olivio Corrêa. All rights reserved.
//

import Foundation
import UIKit

class MovieCollectionViewCell: UICollectionViewCell {
    @IBOutlet weak var uiMovieImage: UIImageView?
    @IBOutlet weak var uiMovieTitle: UILabel?
    @IBOutlet weak var uiMovieRelease: UILabel?
    
    public func setup(movie: Movie) {
        uiMovieImage?.image = UIImage(named: "AppIcon")
        uiMovieImage?.downloaded(from: movie.posterURL)

        uiMovieTitle?.text = movie.title
        
        uiMovieRelease?.text = DateHelper.shared.convertDateFormatter(from: movie.releaseDate)
    }
}

extension UIImageView {
    func downloaded(from url: URL, contentMode mode: UIView.ContentMode = .scaleAspectFill) {
        contentMode = mode
        URLSession.shared.dataTask(with: url) { data, response, error in
            guard
                let httpURLResponse = response as? HTTPURLResponse, httpURLResponse.statusCode == 200,
                let mimeType = response?.mimeType, mimeType.hasPrefix("image"),
                let data = data, error == nil,
                let image = UIImage(data: data)
                else { return }
            DispatchQueue.main.async() {
                self.image = image
            }
            }.resume()
    }
}
