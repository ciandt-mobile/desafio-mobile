//
//  RecommendationsView.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

protocol RecommendationsViewDelegate: class {
    func recommendationsView(_ recommendationsView: RecommendationsView, didSelect recommendation: MovieEntity)
}

class RecommendationsView: UIView, Instantiable {

    // MARK: - Outlets
    
    @IBOutlet weak var collectionView: UICollectionView!
    @IBOutlet weak var collectionViewHeightConstraint: NSLayoutConstraint!

    // MARK: - Internal Properties

    weak var delegate: RecommendationsViewDelegate!
    
    // MARK: - Private Properties

    private var recommendations: [MovieEntity] = []

    // MARK: - Internal Methods

    func setupUI(with recommendations: [MovieEntity]) {
        self.recommendations = recommendations
        self.setupCollectionView()
        self.collectionView.reloadData()
        self.collectionViewHeightConstraint.constant = RecommendationCollectionViewCell.size.height
    }

    // MARK: - Private Methods

    private func setupCollectionView() {
        self.collectionView.dataSource = self
        self.collectionView.delegate = self
        RecommendationCollectionViewCell.registerOn(self.collectionView)
    }
}

// MARK: - UICollectionViewDataSource

extension RecommendationsView: UICollectionViewDataSource {

    // MARK: - Internal Methods

    func collectionView(_ collectionView: UICollectionView,
                        numberOfItemsInSection section: Int) -> Int {
        return self.recommendations.count
    }

    func collectionView(_ collectionView: UICollectionView,
                        cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: RecommendationCollectionViewCell.reuseIdentifier,
                                                            for: indexPath) as? RecommendationCollectionViewCell else { return UICollectionViewCell() }
        cell.setupUI(with: self.recommendations[indexPath.item])
        return cell
    }
}

// MARK: - UICollectionViewDelegateFlowLayout

extension RecommendationsView: UICollectionViewDelegateFlowLayout {

    // MARK: - Internal Methods

    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        sizeForItemAt indexPath: IndexPath) -> CGSize {
        return RecommendationCollectionViewCell.size
    }
}

// MARK: - UICollectionViewDelegate

extension RecommendationsView: UICollectionViewDelegate {

    // MARK: - Internal Methods

    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        self.delegate.recommendationsView(self, didSelect: self.recommendations[indexPath.item])
    }
}
