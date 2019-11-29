//
//  CastCrewView.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

// MARK: - Definitions

struct CastCrewDataSource {

    // MARK: - Internal Properties

    var id: Int
    var creditID: String
    var title: String
    var subtitle: String
    var profilePathURL: String
}

class CastCrewView: UIView, Instantiable {

    // MARK: - Outlets
    
    @IBOutlet weak var collectionView: UICollectionView!
    @IBOutlet weak var collectionViewHeightConstraint: NSLayoutConstraint!
    
    // MARK: - Private Properties

    private var dataSource: [CastCrewDataSource] = []

    // MARK: - Internal Methods

    func setupUI(with cast: [MovieCastEntity]?, and crew: [MovieCrewEntity]?) {

        cast?.forEach { castPerson in
            guard let id = castPerson.id,
                let creditID = castPerson.creditID,
                let title = castPerson.name,
                let subtitle = castPerson.character else { return }
            let castDataSource = CastCrewDataSource(id: id,
                                                    creditID: creditID,
                                                    title: title,
                                                    subtitle: subtitle,
                                                    profilePathURL: castPerson.profilePathURL ?? "")
            self.dataSource.append(castDataSource)
        }

        crew?.forEach { crewPerson in
            guard let id = crewPerson.id,
                let creditID = crewPerson.creditID,
                let title = crewPerson.name,
                let subtitle = crewPerson.job else { return }
            let crewDataSource = CastCrewDataSource(id: id,
                                                    creditID: creditID,
                                                    title: title,
                                                    subtitle: subtitle,
                                                    profilePathURL: crewPerson.profilePathURL ?? "")
            self.dataSource.append(crewDataSource)
        }

        self.setupCollectionView()
        self.collectionView.reloadData()
        self.collectionViewHeightConstraint.constant = CastCrewCollectionViewCell.size.height
    }

    // MARK: - Private Methods

    private func setupCollectionView() {
        self.collectionView.dataSource = self
        self.collectionView.delegate = self
        CastCrewCollectionViewCell.registerOn(self.collectionView)
    }
}

// MARK: - UICollectionViewDataSource

extension CastCrewView: UICollectionViewDataSource {

    // MARK: - Internal Methods

    func collectionView(_ collectionView: UICollectionView,
                        numberOfItemsInSection section: Int) -> Int {
        return self.dataSource.count
    }

    func collectionView(_ collectionView: UICollectionView,
                        cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: CastCrewCollectionViewCell.reuseIdentifier,
                                                            for: indexPath) as? CastCrewCollectionViewCell else { return UICollectionViewCell() }
        cell.setupUI(with: self.dataSource[indexPath.item])
        return cell
    }
}

// MARK: - UICollectionViewDelegateFlowLayout

extension CastCrewView: UICollectionViewDelegateFlowLayout {

    // MARK: - Internal Methods

    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CastCrewCollectionViewCell.size
    }
}
