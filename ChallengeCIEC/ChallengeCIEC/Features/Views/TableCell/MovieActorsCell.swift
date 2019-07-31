//
//  MovieActorsCell.swift
//  ChallengeCIEC
//
//  Created by Guilherme C Rosa on 29/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import UIKit

class MovieActorsCell: UITableViewCell {

    @IBOutlet weak var collection: UICollectionView!
    
    private var credits: MovieCredits?
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func setup(credits: MovieCredits) {
        
        self.credits = credits
        
        self.collection.dataSource = self
        self.collection.delegate = self
        self.collection.reloadData()
    }
    
}

extension MovieActorsCell: UICollectionViewDataSource, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumInteritemSpacingForSectionAt section: Int) -> CGFloat {
        return 0
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        
        let w = (collectionView.frame.width / 3)
        let h = w + (w * 0.4)
        return CGSize(width: w, height: h)
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        
        guard let c = self.credits, let cast = c.cast else { return 0 }
        return cast.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        guard let c = self.credits, let castList = c.cast else { return UICollectionViewCell() }
        
        let properties = CollectionCellBuilderProperties(collection: collectionView, indexPath: indexPath)
        let cast = castList[indexPath.item]
        
        return MovieActorsCollectionCellBuilder(properties: properties, cast: cast).cell
    }
    
}
