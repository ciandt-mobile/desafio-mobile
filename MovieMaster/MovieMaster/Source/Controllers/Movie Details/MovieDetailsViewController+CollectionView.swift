//
//  MovieDetailsViewController+CollectionView.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 01/12/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import UIKit

extension MovieDetailsViewController: UICollectionViewDelegate, UICollectionViewDataSource {
    
    func setupCollectionView() {
        castCollectionView?.delegate = self
        castCollectionView?.dataSource = self
    }

    // MARK: - Collection View
    internal func setupCollectionViewItemSize() {
        if collectionViewFlowLayout == nil {
            let margin = 15
            let width = UIDevice.current.orientation.isLandscape ? (UIScreen.main.bounds.width - CGFloat(4 * margin))/5 : (UIScreen.main.bounds.width - CGFloat(2 * margin))/3
            let lineSpacing: CGFloat = 5
            let internItemSpace: CGFloat = 0

            let size = CGSize(width: width, height: (width * 1.41) + 40)
            collectionViewFlowLayout = UICollectionViewFlowLayout()
            collectionViewFlowLayout.itemSize = size
            collectionViewFlowLayout.sectionInset = .zero
            collectionViewFlowLayout.scrollDirection = .horizontal
            collectionViewFlowLayout.minimumLineSpacing = lineSpacing
            collectionViewFlowLayout.minimumInteritemSpacing = internItemSpace
            castCollectionView.setCollectionViewLayout(collectionViewFlowLayout, animated: true)
            
        }
    }

    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return viewModel?.getCastCount() ?? 0
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        if let cell = collectionView.dequeueReusableCell(withReuseIdentifier: Constants.StringIdentifier.CollectionViewCell.cell, for: indexPath) as? CastCollectionViewCell, let castCrew = viewModel?.getAllCastCrew() {
            cell.cast = castCrew.cast[indexPath.row]
            return cell
        }
        
        return UICollectionViewCell()
    }

    func registerNib(_ nibName: String = Constants.StringIdentifier.CollectionViewCell.castCollectionViewCell) {
        let nibName = UINib(nibName: nibName, bundle: nil)
        castCollectionView.register(nibName, forCellWithReuseIdentifier: Constants.StringIdentifier.CollectionViewCell.cell)
    }

}
