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
        //return galleryImages.count
        return 10
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = galleryCollectionView.dequeueReusableCell(withReuseIdentifier: "cell", for: indexPath) as? ImageMovieCollectionViewCell else {
            return UICollectionViewCell()
        }
        
        cell.layer.cornerRadius = 5
        
//        //setup image in cell
//        let image = galleryImages[indexPath.row]
//
//        if let linkUrl = image.images?.first?.link {
//            let url = URL(string: linkUrl)
//            cell.imageCell.kf.setImage(with: url, placeholder: UIImage(named: "logo_venturus"))
//        }
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        print("Item selecionado!")
    }
}
