//
//  SearchViewController+CollectionView.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 01/12/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import UIKit

extension SearchViewController: UICollectionViewDelegate, UICollectionViewDataSource, UIScrollViewDelegate {

    internal func setupCollectionView() {
        searchCollectionView.delegate = self
        searchCollectionView.dataSource = self
    }

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
            collectionViewFlowLayout.scrollDirection = .vertical
            collectionViewFlowLayout.minimumLineSpacing = lineSpacing
            collectionViewFlowLayout.minimumInteritemSpacing = internItemSpace
            searchCollectionView.setCollectionViewLayout(collectionViewFlowLayout, animated: true)
        }
    }

    internal func scrollViewDidScroll(_ scrollView: UIScrollView) {
        let offSetY = scrollView.contentOffset.y
        let contentHeight = scrollView.contentSize.height
        let movieCurrentPage = viewModel.getPagination().movieCurrentPage ?? 1
        let movieTotalPages = viewModel.getPagination().movieTotalPages ?? 1

        if offSetY > contentHeight - scrollView.frame.size.height * Constants.leadingScreensForBatching {

            if (!fetchingMore && !endReached && (movieCurrentPage + 1 <= movieTotalPages)) {
                searchMovie(page: movieCurrentPage + 1, query: searchString)
            }

        }
    }

    // MARK: - Collection View
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return viewModel.getMoviesCount() ?? 0
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        if let cell = collectionView.dequeueReusableCell(withReuseIdentifier: Constants.StringIdentifier.CollectionViewCell.cell, for: indexPath) as? MovieCollectionViewCell, let searchMovies = viewModel.getMovies() {
            cell.movieCover = searchMovies[indexPath.row]
            return cell
        }
        
        return UICollectionViewCell()
    }

    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        if let movieSelected = viewModel.getMovie(indexPath.row) {
            self.movieSelected = movieSelected
            performSegue(withIdentifier: MovieDetailsViewController.segueIdentifier, sender: self)
        }
    }

    func registerNib(_ nibName: String = Constants.StringIdentifier.CollectionViewCell.movieCollectionViewCell) {
        let nibName = UINib(nibName: nibName, bundle: nil)
        searchCollectionView.register(nibName, forCellWithReuseIdentifier: Constants.StringIdentifier.CollectionViewCell.cell)
    }

}
