//
//  MoviesGalleryViewController+UICollectionViewDelegate.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 23/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import UIKit

//MARK: UICollectionViewDelegate
extension MoviesGalleryViewController: UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        return UIEdgeInsets(top: 12, left: 0, bottom: 12, right: 0)
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        
        var qtdItems: CGFloat = 3
        if UIScreen.main.isLandscape {
            qtdItems = 5
        }
        let width = collectionView.frame.width/qtdItems - 8
        let cellSize = CGSize(width: width, height: width * 1.85)

        return cellSize
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumInteritemSpacingForSectionAt section: Int) -> CGFloat {
        return 8
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return 12
    }
}

extension UIScreen {
    var isLandscape: Bool {
        return self.bounds.width > self.bounds.height
    }
}
