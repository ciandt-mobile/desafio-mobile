//
//  MovieDetailViewController+UICollectionViewDataSource.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 26/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import UIKit

//MARK: UICollectionViewDataSource
extension MovieDetailViewController: UICollectionViewDataSource {
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 10
        //return galleryMovies.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = castCollectionView.dequeueReusableCell(withReuseIdentifier: "castCell", for: indexPath) as? ImageCastCollectionViewCell else {
            return UICollectionViewCell()
        }
        
        cell.layer.cornerRadius = 5
        
//        //setup image in cell
//        let gallery = galleryMovies[indexPath.row]
//        let fullURLImages = "https://image.tmdb.org/t/p/w185_and_h278_bestv2"
//
//        // get movie images
//        if let imageUrl = gallery.posterPath {
//            let url = URL(string: fullURLImages + imageUrl)
//            cell.imageView.kf.setImage(with: url, placeholder: UIImage(named: "sw"))
//        }
//
//        // get title movies
//        cell.titleLabel.text = gallery.originalTitle
//
//        //get date
//        if let date = gallery.releaseDate {
//            cell.dateLabel.text = date
//        }
        
        return cell
    }
}
