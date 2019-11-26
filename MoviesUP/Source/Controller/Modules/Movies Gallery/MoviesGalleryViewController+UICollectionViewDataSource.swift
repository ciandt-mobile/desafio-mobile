//
//  MoviesGalleryViewController+UICollectionViewDataSource.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 23/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import UIKit

//MARK: UICollectionViewDataSource
extension MoviesGalleryViewController: UICollectionViewDataSource {
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return galleryMovies.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = galleryCollectionView.dequeueReusableCell(withReuseIdentifier: "cell", for: indexPath) as? ImageMovieCollectionViewCell else {
            return UICollectionViewCell()
        }
        
        cell.layer.cornerRadius = 5
        
        //setup image in cell
        let gallery = galleryMovies[indexPath.row]
        let fullURLImages = "https://image.tmdb.org/t/p/w185_and_h278_bestv2"

        // get movie images
        if let imageUrl = gallery.posterPath {
            let url = URL(string: fullURLImages + imageUrl)
            cell.imageView.kf.setImage(with: url, placeholder: UIImage(named: "sw"))
        }
        
        // get title movies
        cell.titleLabel.text = gallery.originalTitle
        
        //get date
        if let date = gallery.releaseDate {
            cell.dateLabel.text = date
        }
        
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
//        
//        let mainStoryboard: UIStoryboard = UIStoryboard(name: "MoviesGallery", bundle: nil)
//        let detailVC = mainStoryboard.instantiateViewController(withIdentifier: "movieDetail") as! MovieDetailViewController
//        
//        self.navigationController?.pushViewController(detailVC, animated: true)
    }
}
